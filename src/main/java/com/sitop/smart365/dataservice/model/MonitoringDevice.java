package com.sitop.smart365.dataservice.model;

import javax.persistence.*;

@Table(name = "monitoring_device")
public class MonitoringDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 设备编码
     */
    @Column(name = "device_code")
    private String deviceCode;

    /**
     * 批号
     */
    @Column(name = "batch_number")
    private String batchNumber;

    /**
     * 序列号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 是否业务实体
     */
    @Column(name = "is_business_entity")
    private Boolean isBusinessEntity;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "substation_id")
    private String substationId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * 获取批号
     *
     * @return batch_number - 批号
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * 设置批号
     *
     * @param batchNumber 批号
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * 获取序列号
     *
     * @return serial_number - 序列号
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置序列号
     *
     * @param serialNumber 序列号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取是否业务实体
     *
     * @return is_business_entity - 是否业务实体
     */
    public Boolean getIsBusinessEntity() {
        return isBusinessEntity;
    }

    /**
     * 设置是否业务实体
     *
     * @param isBusinessEntity 是否业务实体
     */
    public void setIsBusinessEntity(Boolean isBusinessEntity) {
        this.isBusinessEntity = isBusinessEntity;
    }

    /**
     * @return customer_id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return substation_id
     */
    public String getSubstationId() {
        return substationId;
    }

    /**
     * @param substationId
     */
    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }
}