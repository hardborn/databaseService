package com.sitop.smart365.dataservice.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "alarm_data_env")
public class AlarmDataEnv {
    @Id
    @Column(name = "alarm_uid")
    private String alarmUid;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "substation_id")
    private Long substationId;

    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "param_id")
    private String paramId;

    @Column(name = "alarm_count")
    private Integer alarmCount;

    @Column(name = "alarm_time")
    private Date alarmTime;

    @Column(name = "alarm_content")
    private String alarmContent;

    @Column(name = "alarm_level")
    private Integer alarmLevel;

    @Column(name = "confirm_state")
    private Integer confirmState;

    @Column(name = "handle_state")
    private Integer handleState;

    @Column(name = "alarm_value")
    private Float alarmValue;

    /**
     * @return alarm_uid
     */
    public String getAlarmUid() {
        return alarmUid;
    }

    /**
     * @param alarmUid
     */
    public void setAlarmUid(String alarmUid) {
        this.alarmUid = alarmUid;
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
     * @return alarm_count
     */
    public Integer getAlarmCount() {
        return alarmCount;
    }

    /**
     * @param alarmCount
     */
    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }

    /**
     * @return alarm_time
     */
    public Date getAlarmTime() {
        return alarmTime;
    }

    /**
     * @param alarmTime
     */
    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    /**
     * @return alarm_content
     */
    public String getAlarmContent() {
        return alarmContent;
    }

    /**
     * @param alarmContent
     */
    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    /**
     * @return alarm_level
     */
    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    /**
     * @param alarmLevel
     */
    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    /**
     * @return confirm_state
     */
    public Integer getConfirmState() {
        return confirmState;
    }

    /**
     * @param confirmState
     */
    public void setConfirmState(Integer confirmState) {
        this.confirmState = confirmState;
    }

    /**
     * @return handle_state
     */
    public Integer getHandleState() {
        return handleState;
    }

    /**
     * @param handleState
     */
    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }

    /**
     * @return alarm_value
     */
    public Float getAlarmValue() {
        return alarmValue;
    }

    /**
     * @param alarmValue
     */
    public void setAlarmValue(Float alarmValue) {
        this.alarmValue = alarmValue;
    }
}