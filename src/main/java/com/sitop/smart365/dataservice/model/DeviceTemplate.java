package com.sitop.smart365.dataservice.model;

import javax.persistence.*;

@Table(name = "device_template")
public class DeviceTemplate {
    /**
     * 设备编码
     */
    @Id
    @Column(name = "device_code")
    private String deviceCode;

    /**
     * 模板数据
     */
    private String template;

    /**
     * 获取设备编码
     *
     * @return device_code - 设备编码
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * 设置设备编码
     *
     * @param deviceCode 设备编码
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    /**
     * 获取模板数据
     *
     * @return template - 模板数据
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 设置模板数据
     *
     * @param template 模板数据
     */
    public void setTemplate(String template) {
        this.template = template;
    }
}