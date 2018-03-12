package com.sitop.smart365.dataservice.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Table(name = "cfg_telemetry_parameter")
public class CfgTelemetryParameter {
    @Id
    @Column(name = "parameter_id")
    @JSONField(name = "szParamID", label = "DataProcessing")
    private String parameterId;

    @Column(name = "device_id")
    @JSONField(serialize = false)
    private String deviceId;

    /**
     * 系数
     */
    @JSONField(name = "fYcCofficient", label = "DataParsing")
    private Double coefficient;

    /**
     * 数据类型
     */
    @Column(name = "data_type")
    @JSONField(name = "nYcDataType", label = "DataParsing")
    private Integer dataType;

    /**
     * 下限
     */
    @Column(name = "lower_bound")
    @JSONField(name = "fYcDownLimit", label = "DataProcessing")
    private Double lowerBound;

    /**
     * 上限
     */
    @Column(name = "upper_bound")
    @JSONField(name = "fYcUpLimit", label = "DataProcessing")
    private Double upperBound;

    /**
     * 告警等级
     */
    @Column(name = "alarm_level")
    @JSONField(name = "nYcAlarmLevel", label = "DataProcessing")
    private Integer alarmLevel;

    /**
     * 是否启用
     */
    @Column(name = "is_enable")
    @JSONField(name = "bYcIsEnable")
    private Boolean isEnable;

    /**
     * 数据类别
     */
    @Column(name = "data_category")
    @JSONField(name = "nYcDataCategory")
    private Integer dataCategory;

    /**
     * 解析序号
     */
    @Column(name = "parsing_order")
    @JSONField(name = "nYcOrder")
    private Integer parsingOrder;

    @Transient
    @JSONField(name = "szEquipmentID", label = "DataProcessing")
    private Long equipmentId;

    @Transient
    @JSONField(name = "nYcParamType", label = "DataProcessing")
    private Integer parameterType;

    @Transient
    @JSONField(name = "nYcParamSubType", label = "DataProcessing")
    private Integer parameterSubType;

    @Transient
    @JSONField(name = "szYcUnit", label = "DataProcessing")
    private String parameterUnit;

    @Transient
    @JSONField(name = "szSubstationID", label = "DataProcessing")
    private String substationId;

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
     * 获取系数
     *
     * @return coefficient - 系数
     */
    public Double getCoefficient() {
        return coefficient;
    }

    /**
     * 设置系数
     *
     * @param coefficient 系数
     */
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * 获取数据类型
     *
     * @return data_type - 数据类型
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型
     *
     * @param dataType 数据类型
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取下限
     *
     * @return lower_bound - 下限
     */
    public Double getLowerBound() {
        return lowerBound;
    }

    /**
     * 设置下限
     *
     * @param lowerBound 下限
     */
    public void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * 获取上限
     *
     * @return upper_bound - 上限
     */
    public Double getUpperBound() {
        return upperBound;
    }

    /**
     * 设置上限
     *
     * @param upperBound 上限
     */
    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    /**
     * 获取告警等级
     *
     * @return alarm_level - 告警等级
     */
    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    /**
     * 设置告警等级
     *
     * @param alarmLevel 告警等级
     */
    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    /**
     * 获取是否启用
     *
     * @return is_enable - 是否启用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用
     *
     * @param isEnable 是否启用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取数据类别
     *
     * @return data_category - 数据类别
     */
    public Integer getDataCategory() {
        return dataCategory;
    }

    /**
     * 设置数据类别
     *
     * @param dataCategory 数据类别
     */
    public void setDataCategory(Integer dataCategory) {
        this.dataCategory = dataCategory;
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

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getParameterType() {
        return parameterType;
    }

    public void setParameterType(Integer parameterType) {
        this.parameterType = parameterType;
    }

    public Integer getParameterSubType() {
        return parameterSubType;
    }

    public void setParameterSubType(Integer parameterSubType) {
        this.parameterSubType = parameterSubType;
    }

    public String getParameterUnit() {
        return parameterUnit;
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }

    public String getSubstationId() {
        return substationId;
    }

    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }
}