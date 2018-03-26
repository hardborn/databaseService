package com.sitop.smart365.dataservice.service;

import com.sitop.smart365.dataservice.model.*;

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

        List<MonitoringDevice> monitoringDevices = monitoringDeviceService.findAll();
        List<MonitoringDevice> monitorEntities = monitoringDevices
                .stream()
                .filter(device -> device.getIsBusinessEntity())
                .collect(toList());

        List<MonitoringDeviceRef> monitoringDeviceRefs = monitoringDeviceRefService.findAll();

        Condition customerIdCondition = new Condition(BaseCustomer.class);
        String customerIdConditionQuery = monitorEntities
                .stream()
                .map(d -> String.format("customer_id = '%s'", d.getCustomerId()))
                .collect(Collectors.joining("OR "));
        customerIdCondition.createCriteria().andCondition(customerIdConditionQuery);
        List<BaseCustomer> customerList = customerService.findByCondition(customerIdCondition);

        for (int index = 0; index < monitorEntities.size(); index++) {
            final MonitoringDevice monitoringEntity = monitorEntities.get(index);
            DataProcessingConfiguration dataProcessingConfig = new DataProcessingConfiguration();

            Optional<BaseCustomer> optionValue = customerList.stream().filter(c -> c.getCustomerId().toString().equals(monitoringEntity.getCustomerId())).findFirst();

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

            Condition deviceIdCondition = new Condition(CfgTelemetryParameter.class);
            String deviceIdConditionQuery = componentDevices
                    .stream()
                    .map(d -> String.format("device_id = '%s'", d.getId()))
                    .collect(Collectors.joining("OR "));
            deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
            List<CfgTelemetryParameter> allDeviceTelemetryParams = telemetryParameterService.findByCondition(deviceIdCondition);

            deviceIdCondition = new Condition(CfgTeleindicationParameter.class);
            deviceIdConditionQuery = componentDevices
                    .stream()
                    .map(d -> String.format("device_id = '%s'", d.getId()))
                    .collect(Collectors.joining("OR "));
            deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
            List<CfgTeleindicationParameter> allDeviceTeleindicationParams = teleindicationParameterService.findByCondition(deviceIdCondition);

            deviceIdCondition = new Condition(CfgElectricityParameter.class);
            deviceIdConditionQuery = componentDevices
                    .stream()
                    .map(d -> String.format("device_id = '%s'", d.getId()))
                    .collect(Collectors.joining("OR "));
            deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
            List<CfgElectricityParameter> allDeviceElectricityParams = electricityParameterService.findByCondition(deviceIdCondition);

            for (MonitoringDevice componentDevice : componentDevices) {
                MonitoringDeviceConfig deviceConfig = new MonitoringDeviceConfig();
                int deviceOrder = deviceRefs
                        .stream()
                        .filter(r -> r.getChildId().equals(componentDevice.getId()))
                        .findFirst().get().getChildOrder();
                deviceConfig.setParsingOrder(deviceOrder);

                List<CfgTelemetryParameter> deviceTelemetryParams = allDeviceTelemetryParams
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .collect(toList());
                modifyAdditionalTelemetryParameters(deviceTelemetryParams);
                deviceConfig.setTelemetryParameterConfigs(deviceTelemetryParams);

                List<CfgTeleindicationParameter> deviceTeleindicationParams = allDeviceTeleindicationParams
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .collect(toList());
                modifyAdditionalTeleindicationParameters(deviceTeleindicationParams);
                deviceConfig.setTelecommunicatingParameterConfigs(deviceTeleindicationParams);

                List<CfgElectricityParameter> deviceElectricityParams = allDeviceElectricityParams
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
        MonitoringDevice monitoringEntity = monitoringDeviceService.findById(entityId);
        if (monitoringEntity == null) {
            return null;
        }

        String currentCustomerId = monitoringEntity.getCustomerId();
        dataProcessingConfig.setGuardId(monitoringEntity.getId());
        dataProcessingConfig.setHeartbeatId("HBT000" + monitoringEntity.getId());
        dataProcessingConfig.setCustomerId(currentCustomerId);

        Condition customerIdCondition = new Condition(BaseCustomer.class);
        String customerIdConditionQuery =
               String.format("customer_id = '%s'", currentCustomerId);
        customerIdCondition.createCriteria().andCondition(customerIdConditionQuery);
        List<BaseCustomer> currentCustomerInfos = customerService.findByCondition(customerIdCondition);

        if (currentCustomerInfos != null && currentCustomerInfos.size() > 0) {
            dataProcessingConfig.setAgentId(currentCustomerInfos.get(0).getAgentId().toString());
        }

        Condition entityIdCondition = new Condition(MonitoringDeviceRef.class);
        String entityIdConditionQuery = String.format("parent_id = '%s'", monitoringEntity.getId());
        entityIdCondition.createCriteria().andCondition(entityIdConditionQuery);
        List<MonitoringDeviceRef> monitoringDeviceRefs = monitoringDeviceRefService.findByCondition(entityIdCondition);

        String componentDeviceIds = monitoringDeviceRefs
                .stream()
                .map(d -> d.getChildId())
                .collect(Collectors.joining(","));

        List<MonitoringDevice> componentDeviceList = monitoringDeviceService.findByIds(componentDeviceIds);

        Condition deviceIdCondition = new Condition(CfgTelemetryParameter.class);
        String deviceIdConditionQuery = componentDeviceList
                .stream()
                .map(d -> String.format("device_id = '%s'", d.getId()))
                .collect(Collectors.joining("OR "));
        deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
        List<CfgTelemetryParameter> allDeviceTelemetryParams = telemetryParameterService.findByCondition(deviceIdCondition);

        deviceIdCondition = new Condition(CfgTeleindicationParameter.class);
        deviceIdConditionQuery = componentDeviceList
                .stream()
                .map(d -> String.format("device_id = '%s'", d.getId()))
                .collect(Collectors.joining("OR "));
        deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
        List<CfgTeleindicationParameter> allDeviceTeleindicationParams = teleindicationParameterService.findByCondition(deviceIdCondition);

        deviceIdCondition = new Condition(CfgElectricityParameter.class);
        deviceIdConditionQuery = componentDeviceList
                .stream()
                .map(d -> String.format("device_id = '%s'", d.getId()))
                .collect(Collectors.joining("OR "));
        deviceIdCondition.createCriteria().andCondition(deviceIdConditionQuery);
        List<CfgElectricityParameter> allDeviceElectricityParams = electricityParameterService.findByCondition(deviceIdCondition);

        List<MonitoringDeviceConfig> monitoringDeviceConfigs = new ArrayList<MonitoringDeviceConfig>();
        for (MonitoringDevice componentDevice : componentDeviceList) {
            MonitoringDeviceConfig deviceConfig = new MonitoringDeviceConfig();
            int deviceOrder = monitoringDeviceRefs
                    .stream()
                    .filter(r -> r.getChildId().equals(componentDevice.getId()))
                    .findFirst().get().getChildOrder();
            deviceConfig.setParsingOrder(deviceOrder);

            List<CfgTelemetryParameter> deviceTelemetryParams = allDeviceTelemetryParams
                    .stream()
                    .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                    .collect(toList());
            modifyAdditionalTelemetryParameters(deviceTelemetryParams);
            deviceConfig.setTelemetryParameterConfigs(deviceTelemetryParams);

            List<CfgTeleindicationParameter> deviceTeleindicationParams = allDeviceTeleindicationParams
                    .stream()
                    .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                    .collect(toList());
            modifyAdditionalTeleindicationParameters(deviceTeleindicationParams);
            deviceConfig.setTelecommunicatingParameterConfigs(deviceTeleindicationParams);

            List<CfgElectricityParameter> deviceElectricityParams = allDeviceElectricityParams
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
                    .map(p -> String.format("param_id = '%s'", p.getParameterId()))
                    .collect(Collectors.joining("OR "));
            parameterIdCondition.createCriteria().andCondition(parameterIdConditionQuery);
            List<CfgEquipmentDeviceRef> equipmentDeviceRefs = equipmentDeviceRefService.findByCondition(parameterIdCondition);
            if (equipmentDeviceRefs.size() != 0) {
                Condition equipmentIdCondition = new Condition(BaseEquipment.class);
                String equipmentIdConditionQuery = equipmentDeviceRefs
                        .stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<CfgEquipmentDeviceRef>((r1, r2) -> r1.getEquipmentId().compareTo(r2.getEquipmentId()))))
                        .stream()
                        .map(r -> String.format("equipment_id = '%s'", r.getEquipmentId()))
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
                    Optional<BaseEquipment> equipment = equipmentList
                            .stream()
                            .filter(e -> e.getEquipmentId().equals(r.getEquipmentId()))
                            .findFirst();
                    if (equipment.isPresent()) {
                        electricityParameter.setSubstationId(equipment.get().getSubstationId().toString());
                    }
                });
            }
        }
    }

    private void modifyAdditionalTelemetryParameters(List<CfgTelemetryParameter> deviceParams) {
        if (deviceParams.size() != 0) {
            Condition parameterIdCondition = new Condition(CfgEquipmentDeviceRef.class);
            String parameterIdConditionQuery = deviceParams
                    .stream()
                    .map(p -> String.format("param_id = '%s'", p.getParameterId()))
                    .collect(Collectors.joining("OR "));
            parameterIdCondition.createCriteria().andCondition(parameterIdConditionQuery);
            List<CfgEquipmentDeviceRef> equipmentDeviceRefs = equipmentDeviceRefService.findByCondition(parameterIdCondition);

            if (equipmentDeviceRefs.size() != 0) {
                Condition equipmentIdCondition = new Condition(BaseEquipment.class);
                String equipmentIdConditionQuery = equipmentDeviceRefs
                        .stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<CfgEquipmentDeviceRef>((r1, r2) -> r1.getEquipmentId().compareTo(r2.getEquipmentId()))))
                        .stream()
                        .map(r -> String.format("equipment_id = '%s'", r.getEquipmentId()))
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
                    Optional<BaseEquipment> equipment = equipmentList
                            .stream()
                            .filter(e -> e.getEquipmentId().equals(r.getEquipmentId()))
                            .findFirst();
                    if (equipment.isPresent()) {
                        parameter.setSubstationId(equipment.get().getSubstationId().toString());
                    }
                });
            }
        }
    }

    private void modifyAdditionalTeleindicationParameters(List<CfgTeleindicationParameter> deviceParams) {
        if (deviceParams.size() != 0) {
            Condition parameterIdCondition = new Condition(CfgEquipmentDeviceRef.class);
            String parameterIdConditionQuery = deviceParams
                    .stream()
                    .map(p -> String.format("param_id = '%s'", p.getParameterId()))
                    .collect(Collectors.joining("OR "));
            parameterIdCondition.createCriteria().andCondition(parameterIdConditionQuery);
            List<CfgEquipmentDeviceRef> equipmentDeviceRefs = equipmentDeviceRefService.findByCondition(parameterIdCondition);

            if (equipmentDeviceRefs.size() != 0) {
                Condition equipmentIdCondition = new Condition(BaseEquipment.class);
                String equipmentIdConditionQuery = equipmentDeviceRefs
                        .stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<CfgEquipmentDeviceRef>((r1, r2) -> r1.getEquipmentId().compareTo(r2.getEquipmentId()))))
                        .stream()
                        .map(r -> String.format("equipment_id = '%s'", r.getEquipmentId()))
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
                    Optional<BaseEquipment> equipment = equipmentList
                            .stream()
                            .filter(e -> e.getEquipmentId().equals(r.getEquipmentId()))
                            .findFirst();
                    if (equipment.isPresent()) {
                        parameter.setSubstationId(equipment.get().getSubstationId().toString());
                    }
                });
            }
        }
    }

}
