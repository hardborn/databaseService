package com.sitop.smart365.dataservice.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class DataProcessingConfiguration {

    @JSONField(name = "szAgentID")
    private String agentId;

    @JSONField(name = "szCustomerID")
    private String customerId;

    @JSONField(name = "szGuardID")
    private String guardId;

    @JSONField(name = "szHBTIdentify")
    private String heartbeatId;

    @JSONField(name = "DeviceInfoArray")
    private List<MonitoringDeviceConfig> monitoringDeviceConfigs;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGuardId() {
        return guardId;
    }

    public void setGuardId(String guardId) {
        this.guardId = guardId;
    }

    public String getHeartbeatId() {
        return heartbeatId;
    }

    public void setHeartbeatId(String heartbeatId) {
        this.heartbeatId = heartbeatId;
    }

    public List<MonitoringDeviceConfig> getMonitoringDeviceConfigs() {
        return monitoringDeviceConfigs;
    }

    public void setMonitoringDeviceConfigs(List<MonitoringDeviceConfig> monitoringDeviceConfigs) {
        this.monitoringDeviceConfigs = monitoringDeviceConfigs;
    }
}
