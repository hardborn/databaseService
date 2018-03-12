package com.sitop.smart365.dataservice.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "data_env_item")
public class DataEnvItem {
    @Id
    @Column(name = "data_item_uid")
    private String dataItemUid;

    @Column(name = "data_uid")
    private String dataUid;

    @Column(name = "point_uid")
    private String pointUid;

    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "substation_id")
    private Long substationId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "create_time")
    private Date createTime;

    private String value;

    @Column(name = "is_alarm")
    private Integer isAlarm;

    @Column(name = "up_limit")
    private Double upLimit;

    @Column(name = "down_limit")
    private Double downLimit;

    /**
     * @return data_item_uid
     */
    public String getDataItemUid() {
        return dataItemUid;
    }

    /**
     * @param dataItemUid
     */
    public void setDataItemUid(String dataItemUid) {
        this.dataItemUid = dataItemUid;
    }

    /**
     * @return data_uid
     */
    public String getDataUid() {
        return dataUid;
    }

    /**
     * @param dataUid
     */
    public void setDataUid(String dataUid) {
        this.dataUid = dataUid;
    }

    /**
     * @return point_uid
     */
    public String getPointUid() {
        return pointUid;
    }

    /**
     * @param pointUid
     */
    public void setPointUid(String pointUid) {
        this.pointUid = pointUid;
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
     * @return agent_id
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * @param agentId
     */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    /**
     * @return substation_id
     */
    public Long getSubstationId() {
        return substationId;
    }

    /**
     * @param substationId
     */
    public void setSubstationId(Long substationId) {
        this.substationId = substationId;
    }

    /**
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return is_alarm
     */
    public Integer getIsAlarm() {
        return isAlarm;
    }

    /**
     * @param isAlarm
     */
    public void setIsAlarm(Integer isAlarm) {
        this.isAlarm = isAlarm;
    }

    /**
     * @return up_limit
     */
    public Double getUpLimit() {
        return upLimit;
    }

    /**
     * @param upLimit
     */
    public void setUpLimit(Double upLimit) {
        this.upLimit = upLimit;
    }

    /**
     * @return down_limit
     */
    public Double getDownLimit() {
        return downLimit;
    }

    /**
     * @param downLimit
     */
    public void setDownLimit(Double downLimit) {
        this.downLimit = downLimit;
    }
}