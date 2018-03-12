package com.sitop.smart365.dataservice.model;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ParsingConfiguration {

    @JSONField(name = "nGuardID")
    private String deviceId;

    @JSONField(name = "szProtocalName")
    private String protocalName;

    @JSONField(name = "szHBTIdentify")
    private String heartbeatId;

    @JSONField(name = "DeviceInfoArray")
    private List<MonitoringDeviceConfig> monitoringDeviceConfigs;

    @JSONField(name = "CmdInfoArray")
    private List<MonitoringCommandInfo> monitoringCommandInfos;

    public List<MonitoringCommandInfo> getMonitoringCommandInfos() {
        return monitoringCommandInfos;
    }

    public void setMonitoringCommandInfos(List<MonitoringCommandInfo> monitoringCommandInfos) {
        this.monitoringCommandInfos = monitoringCommandInfos;
    }

    public List<MonitoringDeviceConfig> getMonitoringDeviceConfigs() {
        return monitoringDeviceConfigs;
    }

    public void setMonitoringDeviceConfigs(List<MonitoringDeviceConfig> monitoringDeviceConfigs) {
        this.monitoringDeviceConfigs = monitoringDeviceConfigs;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProtocalName() {
        return protocalName;
    }

    public void setProtocalName(String protocalName) {
        this.protocalName = protocalName;
    }

    public String getHeartbeatId() {
        return heartbeatId;
    }

    public void setHeartbeatId(String heartbeatId) {
        this.heartbeatId = heartbeatId;
    }
}
