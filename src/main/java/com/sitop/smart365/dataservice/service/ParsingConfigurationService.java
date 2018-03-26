package com.sitop.smart365.dataservice.service;

import com.alibaba.fastjson.JSON;
import com.sitop.smart365.dataservice.model.*;
import com.sitop.smart365.dataservice.utility.LogAspect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ParsingConfigurationService {
    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private MonitoringDeviceCommandService monitoringDeviceCommandService;
    @Resource
    private MonitoringDeviceRefService monitoringDeviceRefService;
    @Resource
    private CfgTelemetryParameterService telemetryParameterService;
    @Resource
    private CfgTeleindicationParameterService teleindicationParameterService;
    @Resource
    private CfgElectricityParameterService electricityParameterService;

    private static final Log LOG = LogFactory.getLog(ParsingConfigurationService.class);

    public List<ParsingConfiguration> getAllParsingConfigs() {
        List<ParsingConfiguration> parsingConfigurationList = new ArrayList<ParsingConfiguration>();
        List<MonitoringDevice> monitoringDevices = monitoringDeviceService.findAll();
        List<MonitoringDevice> monitorEntities = monitoringDevices
                .stream()
                .filter(device -> device.getIsBusinessEntity())
                .collect(toList());

        List<MonitoringDeviceRef> monitoringDeviceRefs = monitoringDeviceRefService.findAll();
        List<MonitoringDeviceCommand> monitoringDeviceCommands = monitoringDeviceCommandService.findAll();
        List<CfgTelemetryParameter> telemetryParameters = telemetryParameterService.findAll();
        List<CfgTeleindicationParameter> teleindicationParameters = teleindicationParameterService.findAll();
        List<CfgElectricityParameter> electricityParameters = electricityParameterService.findAll();


        for (int index = 0; index < monitorEntities.size(); index++) {
            final MonitoringDevice monitoringEntity = monitorEntities.get(index);
            ParsingConfiguration parsingConfiguration = new ParsingConfiguration();
            parsingConfiguration.setDeviceId(monitoringEntity.getId());
            parsingConfiguration.setHeartbeatId("HBT000" + monitoringEntity.getId());
            MonitoringDeviceCommand deviceCommand = monitoringDeviceCommands
                    .stream()
                    .filter(d -> d.getDeviceId().equals(monitoringEntity.getId()))
                    .findFirst().get();
            parsingConfiguration.setProtocalName(deviceCommand.getProtocol().toString());
            List<MonitoringCommandInfo> entityCommandInfo = JSON.parseArray(deviceCommand.getCommandTable(), MonitoringCommandInfo.class);
            entityCommandInfo = entityCommandInfo
                    .stream()
                    .sorted((e1, e2) -> Integer.compare(e1.getCommandId(), e2.getCommandId()))
                    .collect(toList());
            parsingConfiguration.setMonitoringCommandInfos(entityCommandInfo);
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
            for (MonitoringDevice componentDevice : componentDevices) {
                MonitoringDeviceConfig deviceConfig = new MonitoringDeviceConfig();
                int deviceOrder = deviceRefs
                        .stream()
                        .filter(r -> r.getChildId().equals(componentDevice.getId()))
                        .findFirst().get().getChildOrder();
                deviceConfig.setParsingOrder(deviceOrder);
                List<CfgTelemetryParameter> deviceTelemetryParams = telemetryParameters
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder()))
                        .collect(toList());
                deviceConfig.setTelemetryParameterConfigs(deviceTelemetryParams);
                List<CfgTeleindicationParameter> deviceTeleindicationParams = teleindicationParameters
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder()))
                        .collect(toList());
                deviceConfig.setTelecommunicatingParameterConfigs(deviceTeleindicationParams);
                List<CfgElectricityParameter> deviceElectricityParams = electricityParameters
                        .stream()
                        .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                        .sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder()))
                        .collect(toList());
                deviceConfig.setElectricityParameterConfigs(deviceElectricityParams);
                monitoringDeviceConfigs.add(deviceConfig);
            }
            monitoringDeviceConfigs = monitoringDeviceConfigs.stream().sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder())).collect(toList());
            parsingConfiguration.setMonitoringDeviceConfigs(monitoringDeviceConfigs);
            parsingConfigurationList.add(parsingConfiguration);
        }
        return parsingConfigurationList;
    }

    public ParsingConfiguration getParsingConfig(String entityId) {

        ParsingConfiguration parsingConfiguration = new ParsingConfiguration();
        MonitoringDevice monitoringEntity = monitoringDeviceService.findById(entityId);
        if (monitoringEntity == null) {
            return null;
        }

        parsingConfiguration.setDeviceId(monitoringEntity.getId());
        parsingConfiguration.setHeartbeatId("HBT000" + monitoringEntity.getId());

        MonitoringDeviceCommand deviceCommand = monitoringDeviceCommandService.findById(entityId);

        parsingConfiguration.setProtocalName(deviceCommand == null ? null : deviceCommand.getProtocol().toString());
        List<MonitoringCommandInfo> entityCommandInfo = JSON.parseArray(deviceCommand == null ? null : deviceCommand.getCommandTable(), MonitoringCommandInfo.class);
        parsingConfiguration.setMonitoringCommandInfos(entityCommandInfo);

        Condition condition = new Condition(MonitoringDeviceRef.class);
        String conditionQuery = String.format("parent_id = '%s'", entityId);
        condition.createCriteria().andCondition(conditionQuery);
        List<MonitoringDeviceRef> deviceRefs = monitoringDeviceRefService.findByCondition(condition);

        if (deviceRefs == null || deviceRefs.size() == 0) {
            return null;
        }
        String ids = deviceRefs
                .stream()
                .map(r -> r.getChildId())
                .collect(Collectors.joining(","));
        List<MonitoringDeviceConfig> monitoringDeviceConfigs = new ArrayList<MonitoringDeviceConfig>();

        List<MonitoringDevice> componentDevices = monitoringDeviceService.findByIds(ids);

        if (componentDevices == null || componentDevices.size() == 0) {
            return null;
        }
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
                    .sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder()))
                    .collect(toList());

            deviceConfig.setTelemetryParameterConfigs(deviceTelemetryParams);

            List<CfgTeleindicationParameter> deviceTeleindicationParams = allDeviceTeleindicationParams
                    .stream()
                    .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                    .sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder()))
                    .collect(toList());

            deviceConfig.setTelecommunicatingParameterConfigs(deviceTeleindicationParams);

            List<CfgElectricityParameter> deviceElectricityParams = allDeviceElectricityParams
                    .stream()
                    .filter(p -> p.getDeviceId().equals(componentDevice.getId()))
                    .sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder()))
                    .collect(toList());

            deviceConfig.setElectricityParameterConfigs(deviceElectricityParams);

            monitoringDeviceConfigs.add(deviceConfig);
        }
        monitoringDeviceConfigs = monitoringDeviceConfigs.stream().sorted((e1, e2) -> Integer.compare(e1.getParsingOrder(), e2.getParsingOrder())).collect(toList());
        parsingConfiguration.setMonitoringDeviceConfigs(monitoringDeviceConfigs);
        return parsingConfiguration;
    }
}


