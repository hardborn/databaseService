package com.sitop.smart365.dataservice.service;

import com.sitop.smart365.dataservice.model.*;
import com.sun.javafx.binding.StringFormatter;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class DataProcessingConfigurationService {
    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private CfgEquipmentDeviceRefService equipmentDeviceRefService;
    @Resource
    private MonitoringDeviceRefService monitoringDeviceRefService;
    @Resource
    private CfgTelemetryParameterService telemetryParameterService;
    @Resource
    private CfgTeleindicationParameterService teleindicationParameterService;
    @Resource
    private CfgElectricityParameterService electricityParameterService;
    @Resource
    private BaseCustomerService customerService;
    @Resource
    private BaseEquipmentService equipmentService;

    private static final Log LOG = LogFactory.getLog(ParsingConfigurationService.class);

    public List<DataProcessingConfiguration> getAllDataProcessingConfigs() {
        List<DataProcessingConfiguration> dataProcessingConfigList = new ArrayList<>();

        long start = System.currentTimeMillis();
        List<MonitoringDevice> monitoringDevices = monitoringDeviceService.findAll();
        List<MonitoringDevice> monitorEntities = monitoringDevices
                .stream()
                .filter(device -> device.getIsBusinessEntity())
                .collect(toList());
        long end = System.currentTimeMillis();
        LOG.info("+++++ monitoringDeviceService.findAll" + "\tUse time : " + (end - start) + " ms!");

         start = System.currentTimeMillis();
        List<MonitoringDeviceRef> monitoringDeviceRefs = monitoringDeviceRefService.findAll();
         end = System.currentTimeMillis();
        LOG.info("+++++ monitoringDeviceRefService.findAll" + "\tUse time : " + (end - start) + " ms!");

        start = System.currentTimeMillis();
        Condition customerIdCondition = new Condition(CfgTelemetryParameter.class);
        String customerIdConditionQuery = monitorEntities
                .stream()
                .map(d -> StringFormatter.format("customer_id = '%s'", d.getCustomerId()).getValue())
                .collect(Collectors.joining("OR "));
        customerIdCondition.createCriteria().andCondition(customerIdConditionQuery);
        List<BaseCustomer> customerList = customerService.findByCondition(customerIdCondition);
        end = System.currentTimeMillis();
        LOG.info("+++++ customerService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

        for (int index = 0; index < monitorEntities.size(); index++) {
            final MonitoringDevice monitoringEntity = monitorEntities.get(index);
            DataProcessingConfiguration dataProcessingConfig = new DataProcessingConfiguration();

            Optional<BaseCustomer> optionValue = customerList.stream().filter(c -> c.getCustomerId().equals(monitoringEntity.getCustomerId())).findFirst();

            BaseCustomer customer = optionValue.orElse(null);
            dataProcessingConfig.setGuardId(monitoringEntity.getId());
            dataProcessingConfig.setHeartbeatId("HBT" + monitoringEntity.getId());
            dataProcessingConfig.setAgentId(customer == null ? null : customer.getAgentId().toString());
            dataProcessingConfig.setCustomerId(customer == null ? null : customer.getCustomerId().toString());

            List<MonitoringDeviceRef> deviceRefs = monitoringDeviceRefs
                    .stream()
                    .filter(r -> r.getParentId().equals(monitoringEntity.getId()))
                    .collect(toList());
            List<MonitoringDeviceConfig> monitoringDeviceConfigs = new ArrayList<MonitoringDeviceConfig>();
            List<MonitoringDevice> componentDevices = monitoringDevices
                    .stream()
                    .filter(d -> deviceRefs
                            .stream()
                            .map(r -> r.getChildId())
                            .collect(toList())
                            .contains(d.getId()))
                    .collect(toList());

            start = System.currentTimeMillis();
            Condition deviceIdCondition = new Condition(CfgTelemetryParameter.class);
            String deviceIdConditionQuery =componentDevices
                    .stream()
                    .map(d -> StringFormatter.format("device_id = '%s'", d.getId()).getValue())
                    .collect(Collectors.joining("OR "));
            deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
            List<CfgTelemetryParameter> allDeviceTelemetryParams = telemetryParameterService.findByCondition(deviceIdCondition);
            end = System.currentTimeMillis();
            LOG.info("+++++ telemetryParameterService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

            start = System.currentTimeMillis();
            deviceIdCondition = new Condition(CfgTeleindicationParameter.class);
            deviceIdConditionQuery =componentDevices
                    .stream()
                    .map(d -> StringFormatter.format("device_id = '%s'", d.getId()).getValue())
                    .collect(Collectors.joining("OR "));
            deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
            List<CfgTeleindicationParameter> allDeviceTeleindicationParams = teleindicationParameterService.findByCondition(deviceIdCondition);
            end = System.currentTimeMillis();
            LOG.info("+++++ teleindicationParameterService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

            start = System.currentTimeMillis();
            deviceIdCondition = new Condition(CfgElectricityParameter.class);
            deviceIdConditionQuery =componentDevices
                    .stream()
                    .map(d -> StringFormatter.format("device_id = '%s'", d.getId()).getValue())
                    .collect(Collectors.joining("OR "));
            deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
            List<CfgElectricityParameter> allDeviceElectricityParams = electricityParameterService.findByCondition(deviceIdCondition);
            end = System.currentTimeMillis();
            LOG.info("+++++ electricityParameterService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

            for (MonitoringDevice componentDevice : componentDevices) {
                MonitoringDeviceConfig deviceConfig = new MonitoringDeviceConfig();
                int deviceOrder = deviceRefs
                        .stream()
                        .filter(r -> r.getChildId().equals(componentDevice.getId()))
                        .findFirst().get().getChildOrder();
                deviceConfig.setParsingOrder(deviceOrder);

                List<CfgTelemetryParameter> deviceTelemetryParams =  allDeviceTelemetryParams
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .collect(toList());
                modifyAdditionalTelemetryParameters(deviceTelemetryParams);
                deviceConfig.setTelemetryParameterConfigs(deviceTelemetryParams);

                List<CfgTeleindicationParameter> deviceTeleindicationParams =  allDeviceTeleindicationParams
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .collect(toList());
                modifyAdditionalTeleindicationParameters(deviceTeleindicationParams);
                deviceConfig.setTelecommunicatingParameterConfigs(deviceTeleindicationParams);

                List<CfgElectricityParameter> deviceElectricityParams =  allDeviceElectricityParams
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .collect(toList());
                modifyAdditionalElectricityParameters(deviceElectricityParams);
                deviceConfig.setElectricityParameterConfigs(deviceElectricityParams);

                monitoringDeviceConfigs.add(deviceConfig);
            }
            dataProcessingConfig.setMonitoringDeviceConfigs(monitoringDeviceConfigs);
            dataProcessingConfigList.add(dataProcessingConfig);
        }

        return dataProcessingConfigList;
    }

    public DataProcessingConfiguration getDataProcessingConfigs(String entityId) {
        DataProcessingConfiguration dataProcessingConfig = new DataProcessingConfiguration();
        long start = System.currentTimeMillis();
        MonitoringDevice monitoringEntity = monitoringDeviceService.findById(entityId);
        if (monitoringEntity == null) {
            return null;
        }
        long end = System.currentTimeMillis();
        LOG.info("+++++ monitoringDeviceService.findById" + "\tUse time : " + (end - start) + " ms!");

        String currentCustomerId = monitoringEntity.getCustomerId();
        dataProcessingConfig.setGuardId(monitoringEntity.getId());
        dataProcessingConfig.setHeartbeatId("HBT000" + monitoringEntity.getId());
        dataProcessingConfig.setCustomerId(currentCustomerId);

        start = System.currentTimeMillis();
        BaseCustomer currentCustomerInfo = customerService.findById(currentCustomerId);
        end = System.currentTimeMillis();
        LOG.info("+++++ customerService.findById" + "\tUse time : " + (end - start) + " ms!");

        if (currentCustomerInfo != null) {
            dataProcessingConfig.setAgentId(currentCustomerInfo.getAgentId().toString());
        }

        start = System.currentTimeMillis();
        Condition entityIdCondition = new Condition(MonitoringDeviceRef.class);
        String entityIdConditionQuery = StringFormatter.format("parent_id = '%s'", monitoringEntity.getId()).getValue();
        entityIdCondition.createCriteria().andCondition(entityIdConditionQuery);
        List<MonitoringDeviceRef> monitoringDeviceRefs = monitoringDeviceRefService.findByCondition(entityIdCondition);
        end = System.currentTimeMillis();
        LOG.info("+++++ monitoringDeviceRefService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

        String componentDeviceIds = monitoringDeviceRefs
                .stream()
                .map(d -> d.getChildId())
                .collect(Collectors.joining(","));

        start = System.currentTimeMillis();
        List<MonitoringDevice> componentDeviceList = monitoringDeviceService.findByIds(componentDeviceIds);
        end = System.currentTimeMillis();
        LOG.info("+++++ monitoringDeviceService.findByIds" + "\tUse time : " + (end - start) + " ms!");

        start = System.currentTimeMillis();
        Condition deviceIdCondition = new Condition(CfgTelemetryParameter.class);
        String deviceIdConditionQuery =componentDeviceList
                .stream()
                .map(d -> StringFormatter.format("device_id = '%s'", d.getId()).getValue())
                .collect(Collectors.joining("OR "));
        deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
        List<CfgTelemetryParameter> allDeviceTelemetryParams = telemetryParameterService.findByCondition(deviceIdCondition);
        end = System.currentTimeMillis();
        LOG.info("+++++ telemetryParameterService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

        start = System.currentTimeMillis();
        deviceIdCondition = new Condition(CfgTeleindicationParameter.class);
        deviceIdConditionQuery =componentDeviceList
                .stream()
                .map(d -> StringFormatter.format("device_id = '%s'", d.getId()).getValue())
                .collect(Collectors.joining("OR "));
        deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
        List<CfgTeleindicationParameter> allDeviceTeleindicationParams = teleindicationParameterService.findByCondition(deviceIdCondition);
        end = System.currentTimeMillis();
        LOG.info("+++++ teleindicationParameterService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

        start = System.currentTimeMillis();
        deviceIdCondition = new Condition(CfgElectricityParameter.class);
        deviceIdConditionQuery =componentDeviceList
                .stream()
                .map(d -> StringFormatter.format("device_id = '%s'", d.getId()).getValue())
                .collect(Collectors.joining("OR "));
        deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
        List<CfgElectricityParameter> allDeviceElectricityParams = electricityParameterService.findByCondition(deviceIdCondition);
        end = System.currentTimeMillis();
        LOG.info("+++++ electricityParameterService.findByCondition" + "\tUse time : " + (end - start) + " ms!");

        List<MonitoringDeviceConfig> monitoringDeviceConfigs = new ArrayList<MonitoringDeviceConfig>();
        for (MonitoringDevice componentDevice : componentDeviceList) {
            MonitoringDeviceConfig deviceConfig = new MonitoringDeviceConfig();
            int deviceOrder = monitoringDeviceRefs
                    .stream()
                    .filter(r -> r.getChildId().equals(componentDevice.getId()))
                    .findFirst().get().getChildOrder();
            deviceConfig.setParsingOrder(deviceOrder);

            List<CfgTelemetryParameter> deviceTelemetryParams =  allDeviceTelemetryParams
                    .stream()
                    .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                    .collect(toList());
            modifyAdditionalTelemetryParameters(deviceTelemetryParams);
            deviceConfig.setTelemetryParameterConfigs(deviceTelemetryParams);

            List<CfgTeleindicationParameter> deviceTeleindicationParams =  allDeviceTeleindicationParams
                    .stream()
                    .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                    .collect(toList());
            modifyAdditionalTeleindicationParameters(deviceTeleindicationParams);
            deviceConfig.setTelecommunicatingParameterConfigs(deviceTeleindicationParams);

            List<CfgElectricityParameter> deviceElectricityParams =  allDeviceElectricityParams
                    .stream()
                    .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                    .collect(toList());
            modifyAdditionalElectricityParameters(deviceElectricityParams);
            deviceConfig.setElectricityParameterConfigs(deviceElectricityParams);

            monitoringDeviceConfigs.add(deviceConfig);
        }
        dataProcessingConfig.setMonitoringDeviceConfigs(monitoringDeviceConfigs);
        return dataProcessingConfig;
    }

    private void modifyAdditionalElectricityParameters(List<CfgElectricityParameter> deviceElectricityParams) {
        if (deviceElectricityParams.size() != 0) {
            Condition parameterIdCondition = new Condition(CfgEquipmentDeviceRef.class);
            String parameterIdConditionQuery = deviceElectricityParams
                    .stream()
                    .map(p -> StringFormatter.format("param_id = '%s'", p.getParameterId()).getValue())
                    .collect(Collectors.joining("OR "));
            parameterIdCondition.createCriteria().andCondition(parameterIdConditionQuery);
            List<CfgEquipmentDeviceRef> equipmentDeviceRefs = equipmentDeviceRefService.findByCondition(parameterIdCondition);
            if (equipmentDeviceRefs.size() != 0) {
                Condition equipmentIdCondition = new Condition(BaseEquipment.class);
                String equipmentIdConditionQuery = equipmentDeviceRefs
                        .stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<CfgEquipmentDeviceRef>((r1, r2) -> r1.getEquipmentId().compareTo(r2.getEquipmentId()))))
                        .stream()
                        .map(r -> StringFormatter.format("equipment_id = '%s'", r.getEquipmentId()).getValue())
                        .collect(Collectors.joining("OR "));
                equipmentIdCondition.createCriteria().andCondition(equipmentIdConditionQuery);
                List<BaseEquipment> equipmentList = equipmentService.findByCondition(equipmentIdCondition);

                equipmentDeviceRefs.forEach(r -> {
                    CfgElectricityParameter electricityParameter = deviceElectricityParams
                            .stream()
                            .filter(p -> p.getParameterId().equals(r.getParamId()))
                            .findFirst().get();
                    electricityParameter.setEquipmentId(r.getEquipmentId());
                    electricityParameter.setParameterType(r.getParamType());
                    electricityParameter.setParameterSubType(r.getParamTypeSub());
                    electricityParameter.setParameterUnit(r.getUnit());
                    electricityParameter.setSubstationId(equipmentList
                            .stream()
                            .filter(e -> e.getEquipmentId().equals(r.getEquipmentId()))
                            .findFirst()
                            .get()
                            .getSubstationId().toString());
                    // electricityParameter.setSubstationId();
                });
            }
        }
    }

    private void modifyAdditionalTelemetryParameters(List<CfgTelemetryParameter> deviceParams) {
        if (deviceParams.size() != 0) {
            Condition parameterIdCondition = new Condition(CfgEquipmentDeviceRef.class);
            String parameterIdConditionQuery = deviceParams
                    .stream()
                    .map(p -> StringFormatter.format("param_id = '%s'", p.getParameterId()).getValue())
                    .collect(Collectors.joining("OR "));
            parameterIdCondition.createCriteria().andCondition(parameterIdConditionQuery);
            List<CfgEquipmentDeviceRef> equipmentDeviceRefs = equipmentDeviceRefService.findByCondition(parameterIdCondition);

            if (equipmentDeviceRefs.size() != 0) {
                Condition equipmentIdCondition = new Condition(BaseEquipment.class);
                String equipmentIdConditionQuery = equipmentDeviceRefs
                        .stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<CfgEquipmentDeviceRef>((r1, r2) -> r1.getEquipmentId().compareTo(r2.getEquipmentId()))))
                        .stream()
                        .map(r -> StringFormatter.format("equipment_id = '%s'", r.getEquipmentId()).getValue())
                        .collect(Collectors.joining("OR "));
                equipmentIdCondition.createCriteria().andCondition(equipmentIdConditionQuery);
                List<BaseEquipment> equipmentList = equipmentService.findByCondition(equipmentIdCondition);

                equipmentDeviceRefs.forEach(r -> {
                    CfgTelemetryParameter parameter = deviceParams
                            .stream()
                            .filter(p -> p.getParameterId().equals(r.getParamId()))
                            .findFirst().get();
                    parameter.setEquipmentId(r.getEquipmentId());
                    parameter.setParameterType(r.getParamType());
                    parameter.setParameterSubType(r.getParamTypeSub());
                    parameter.setParameterUnit(r.getUnit());
                    parameter.setSubstationId(equipmentList
                            .stream()
                            .filter(e -> e.getEquipmentId().equals(r.getEquipmentId()))
                            .findFirst()
                            .get()
                            .getSubstationId().toString());
                });
            }
        }
    }

    private void modifyAdditionalTeleindicationParameters(List<CfgTeleindicationParameter> deviceParams) {
        if (deviceParams.size() != 0) {
            Condition parameterIdCondition = new Condition(CfgEquipmentDeviceRef.class);
            String parameterIdConditionQuery = deviceParams
                    .stream()
                    .map(p -> StringFormatter.format("param_id = '%s'", p.getParameterId()).getValue())
                    .collect(Collectors.joining("OR "));
            parameterIdCondition.createCriteria().andCondition(parameterIdConditionQuery);
            List<CfgEquipmentDeviceRef> equipmentDeviceRefs = equipmentDeviceRefService.findByCondition(parameterIdCondition);

            if (equipmentDeviceRefs.size() != 0) {
                Condition equipmentIdCondition = new Condition(BaseEquipment.class);
                String equipmentIdConditionQuery = equipmentDeviceRefs
                        .stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<CfgEquipmentDeviceRef>((r1, r2) -> r1.getEquipmentId().compareTo(r2.getEquipmentId()))))
                        .stream()
                        .map(r -> StringFormatter.format("equipment_id = '%s'", r.getEquipmentId()).getValue())
                        .collect(Collectors.joining("OR "));
                equipmentIdCondition.createCriteria().andCondition(equipmentIdConditionQuery);
                List<BaseEquipment> equipmentList = equipmentService.findByCondition(equipmentIdCondition);

                equipmentDeviceRefs.forEach(r -> {
                    CfgTeleindicationParameter parameter = deviceParams
                            .stream()
                            .filter(p -> p.getParameterId().equals(r.getParamId()))
                            .findFirst().get();
                    parameter.setEquipmentId(r.getEquipmentId());
                    parameter.setParameterType(r.getParamType());
                    parameter.setParameterSubType(r.getParamTypeSub());
                    parameter.setSubstationId(equipmentList
                            .stream()
                            .filter(e -> e.getEquipmentId().equals(r.getEquipmentId()))
                            .findFirst()
                            .get()
                            .getSubstationId().toString());
                });
            }
        }
    }

}
