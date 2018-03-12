package com.sitop.smart365.dataservice.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MonitoringDeviceConfig {
    @JSONField(name = "nDeviceOrder")
    private int parsingOrder;
    @JSONField(name = "YcInfoArray")
    private List<CfgTelemetryParameter> telemetryParameterConfigs;
    @JSONField(name = "YxInfoArray")
    private List<CfgTeleindicationParameter> telecommunicatingParameterConfigs;
    @JSONField(name = "DdInfoArray")
    private List<CfgElectricityParameter> electricityParameterConfigs;


    public int getParsingOrder() {
        return parsingOrder;
    }

    public void setParsingOrder(int parsingOrder) {
        this.parsingOrder = parsingOrder;
    }

    public List<CfgTelemetryParameter> getTelemetryParameterConfigs() {
        return telemetryParameterConfigs;
    }

    public void setTelemetryParameterConfigs(List<CfgTelemetryParameter> telemetryParameterConfigs) {
        this.telemetryParameterConfigs = telemetryParameterConfigs;
    }

    public List<CfgTeleindicationParameter> getTelecommunicatingParameterConfigs() {
        return telecommunicatingParameterConfigs;
    }

    public void setTelecommunicatingParameterConfigs(List<CfgTeleindicationParameter> telecommunicatingParameterConfigs) {
        this.telecommunicatingParameterConfigs = telecommunicatingParameterConfigs;
    }

    public List<CfgElectricityParameter> getElectricityParameterConfigs() {
        return electricityParameterConfigs;
    }

    public void setElectricityParameterConfigs(List<CfgElectricityParameter> electricityParameterConfigs) {
        this.electricityParameterConfigs = electricityParameterConfigs;
    }
}
