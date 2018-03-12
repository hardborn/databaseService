package com.sitop.smart365.dataservice.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Table(name = "cfg_telecommunicating_parameter")
public class CfgTelecommunicatingParameter {
    @Id
    @Column(name = "parameter_id")
    @JSONField(name = "szParamID", label = "DataProcessing")
    private String parameterId;

    @Column(name = "device_id")
    @JSONField(serialize = false)
    private String deviceId;

    /**
     * 解析序号
     */
    @Column(name = "parsing_order")
    @JSONField(name = "nYxOrder")
    private Integer parsingOrder;

    /**
     * 是否倒置
     */
    @JSONField(name = "bYxIsNot")
    private Boolean inversion;

    /**
     * 是否告警
     */
    @Column(name = "alarm_level")
    @JSONField(serialize = false)
    private Integer alarmLevel;

    @Column(name = "is_enable")
    @JSONField(serialize = false)
    private Boolean isEnable;

    /**
     * @return parameter_id
     */
    public String getParameterId() {
        return parameterId;
    }

    /**
     * @param parameterId
     */
    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

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
     * 获取解析序号
     *
     * @return parsing_order - 解析序号
     */
    public Integer getParsingOrder() {
        return parsingOrder;
    }

    /**
     * 设置解析序号
     *
     * @param parsingOrder 解析序号
     */
    public void setParsingOrder(Integer parsingOrder) {
        this.parsingOrder = parsingOrder;
    }

    /**
     * 获取是否倒置
     *
     * @return inversion - 是否倒置
     */
    public Boolean getInversion() {
        return inversion;
    }

    /**
     * 设置是否倒置
     *
     * @param inversion 是否倒置
     */
    public void setInversion(Boolean inversion) {
        this.inversion = inversion;
    }

    /**
     * 获取是否告警
     *
     * @return alarm_level - 是否告警
     */
    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    /**
     * 设置是否告警
     *
     * @param alarmLevel 是否告警
     */
    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    /**
     * @return is_enable
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * @param isEnable
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
}