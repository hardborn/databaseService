package com.sitop.smart365.dataservice.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Table(name = "cfg_electricity_parameter")
public class CfgElectricityParameter {
    @Id
    @Column(name = "parameter_id")
    private String parameterId;

    @Column(name = "device_id")
    private String deviceId;

    /**
     * 系数
     */
    private Double coefficient;

    @Transient
    private Long equipmentId;

    @Transient
    private Integer parameterType;

    @Transient
    private Integer parameterSubType;

    @Transient
    private String parameterUnit;

    @Transient
    private String substationId;
    /**
     * 数据类型
     */
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 下限
     */
    @Column(name = "lower_bound")
    private Double lowerBound;

    /**
     * 上限
     */
    @Column(name = "upper_bound")
    private Double upperBound;

    /**
     * 告警等级
     */
    @Column(name = "alarm_level")
    private Integer alarmLevel;

    /**
     * 是否启用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 数据类别
     */
    @Column(name = "data_category")
    private Integer dataCategory;

    /**
     * 解析序号
     */
    @Column(name = "parsing_order")
    private Integer parsingOrder;

    /**
     * @return parameter_id
     */
    @JSONField(name = "szParamID",label = "DataProcessing")
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
    @JSONField(serialize = false)
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
    @JSONField(name = "fDdCofficient",label = "DataParsing")
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
    @JSONField(name = "nDdDataType",label = "DataParsing")
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
    @JSONField(name = "fDdDownLimit",label = "DataProcessing")
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
    @JSONField(name = "fDdUpLimit",label = "DataProcessing")
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
    @JSONField(name = "nDdAlarmLevel",label = "DataProcessing")
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
    @JSONField(name="bDdIsEnable",label = "DataProcessing")
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
    @JSONField(name = "nDdDataCategory")
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
    @JSONField(name = "nDdOrder")
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

    @JSONField(name="szEquipmentID",label = "DataProcessing")
    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    @JSONField(name="nDdParamType",label = "DataProcessing")
    public Integer getParameterType() {
        return parameterType;
    }

    public void setParameterType(Integer parameterType) {
        this.parameterType = parameterType;
    }

    @JSONField(name="nDdParamSubType",label = "DataProcessing")
    public Integer getParameterSubType() {
        return parameterSubType;
    }

    public void setParameterSubType(Integer parameterSubType) {
        this.parameterSubType = parameterSubType;
    }

    @JSONField(name="szDdUnit",label = "DataProcessing")
    public String getParameterUnit() {
        return parameterUnit;
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }

    @JSONField(name="szSubstationID",label = "DataProcessing")
    public String getSubstationId() {
        return substationId;
    }

    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }
}