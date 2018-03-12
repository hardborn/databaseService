package com.sitop.smart365.dataservice.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "data_env")
public class DataEnv {
    @Id
    @Column(name = "data_uid")
    private String dataUid;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "substation_id")
    private Long substationId;

    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_alarm")
    private Integer isAlarm;

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
}