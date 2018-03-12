package com.sitop.smart365.dataservice.model;

import javax.persistence.*;

@Table(name = "cfg_equipment_device_ref")
public class CfgEquipmentDeviceRef {
    @Id
    private String uid;

    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "data_type")
    private Integer dataType;

    @Column(name = "point_number")
    private Integer pointNumber;

    @Column(name = "param_id")
    private String paramId;

    @Column(name = "param_type")
    private Integer paramType;

    @Column(name = "param_type_sub")
    private Integer paramTypeSub;

    @Column(name = "param_name")
    private String paramName;

    private String unit;

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return equipment_id
     */
    public Long getEquipmentId() {
        return equipmentId;
    }

    /**
     * @param equipmentId
     */
    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
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
     * @return data_type
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * @return point_number
     */
    public Integer getPointNumber() {
        return pointNumber;
    }

    /**
     * @param pointNumber
     */
    public void setPointNumber(Integer pointNumber) {
        this.pointNumber = pointNumber;
    }

    /**
     * @return param_id
     */
    public String getParamId() {
        return paramId;
    }

    /**
     * @param paramId
     */
    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    /**
     * @return param_type
     */
    public Integer getParamType() {
        return paramType;
    }

    /**
     * @param paramType
     */
    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    /**
     * @return param_type_sub
     */
    public Integer getParamTypeSub() {
        return paramTypeSub;
    }

    /**
     * @param paramTypeSub
     */
    public void setParamTypeSub(Integer paramTypeSub) {
        this.paramTypeSub = paramTypeSub;
    }

    /**
     * @return param_name
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
}