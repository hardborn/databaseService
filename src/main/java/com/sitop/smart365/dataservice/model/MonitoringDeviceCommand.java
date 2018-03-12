package com.sitop.smart365.dataservice.model;

import javax.persistence.*;

@Table(name = "monitoring_device_command")
public class MonitoringDeviceCommand {
    @Id
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 通讯协议
     */
    private String protocol;

    /**
     * 通讯命令表
     */
    @Column(name = "command_table")
    private String commandTable;

    /**
     * @return device_id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取通讯协议
     *
     * @return protocol - 通讯协议
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * 设置通讯协议
     *
     * @param protocol 通讯协议
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * 获取通讯命令表
     *
     * @return command_table - 通讯命令表
     */
    public String getCommandTable() {
        return commandTable;
    }

    /**
     * 设置通讯命令表
     *
     * @param commandTable 通讯命令表
     */
    public void setCommandTable(String commandTable) {
        this.commandTable = commandTable;
    }
}