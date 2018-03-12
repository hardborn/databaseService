package com.sitop.smart365.dataservice.model;

import javax.persistence.*;

@Table(name = "monitoring_device_parameter")
public class MonitoringDeviceParameter {
    /**
     * 设备id
     */
    @Id
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 参数id
     */
    @Column(name = "parameter_id")
    private String parameterId;

    /**
     * 参数主类型
     */
    @Column(name = "parameter_type")
    private Integer parameterType;

    /**
     * 参数子类型
     */
    @Column(name = "parameter_sub_type")
    private Integer parameterSubType;

    /**
     * 参数实例名称
     */
    @Column(name = "parameter_name")
    private String parameterName;

    /**
     * 获取设备id
     *
     * @return device_id - 设备id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备id
     *
     * @param deviceId 设备id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取参数id
     *
     * @return parameter_id - 参数id
     */
    public String getParameterId() {
        return parameterId;
    }

    /**
     * 设置参数id
     *
     * @param parameterId 参数id
     */
    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    /**
     * 获取参数主类型
     *
     * @return parameter_type - 参数主类型
     */
    public Integer getParameterType() {
        return parameterType;
    }

    /**
     * 设置参数主类型
     *
     * @param parameterType 参数主类型
     */
    public void setParameterType(Integer parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * 获取参数子类型
     *
     * @return parameter_sub_type - 参数子类型
     */
    public Integer getParameterSubType() {
        return parameterSubType;
    }

    /**
     * 设置参数子类型
     *
     * @param parameterSubType 参数子类型
     */
    public void setParameterSubType(Integer parameterSubType) {
        this.parameterSubType = parameterSubType;
    }

    /**
     * 获取参数实例名称
     *
     * @return parameter_name - 参数实例名称
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * 设置参数实例名称
     *
     * @param parameterName 参数实例名称
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
}