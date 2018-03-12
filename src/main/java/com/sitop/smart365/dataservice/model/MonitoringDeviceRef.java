package com.sitop.smart365.dataservice.model;

import javax.persistence.*;

@Table(name = "monitoring_device_ref")
public class MonitoringDeviceRef {
    /**
     * 父设备id
     */
    @Id
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 子设备id
     */
    @Column(name = "child_id")
    private String childId;

    /**
     * 子设备次序号
     */
    @Column(name = "child_order")
    private Integer childOrder;

    /**
     * 获取父设备id
     *
     * @return parent_id - 父设备id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父设备id
     *
     * @param parentId 父设备id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取子设备id
     *
     * @return child_id - 子设备id
     */
    public String getChildId() {
        return childId;
    }

    /**
     * 设置子设备id
     *
     * @param childId 子设备id
     */
    public void setChildId(String childId) {
        this.childId = childId;
    }

    /**
     * 获取子设备次序号
     *
     * @return child_order - 子设备次序号
     */
    public Integer getChildOrder() {
        return childOrder;
    }

    /**
     * 设置子设备次序号
     *
     * @param childOrder 子设备次序号
     */
    public void setChildOrder(Integer childOrder) {
        this.childOrder = childOrder;
    }
}