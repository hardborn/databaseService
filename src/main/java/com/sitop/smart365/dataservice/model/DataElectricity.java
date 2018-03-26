package com.sitop.smart365.dataservice.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "data_electricity")
public class DataElectricity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 数据ID
     */
    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "data_time")
    private Date dataTime;

    /**
     * 检测值
     */
    @Column(name = "integral_point")
    private String integralPoint;

    private Float value;

    @Column(name = "param_type")
    private Integer paramType;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取数据ID
     *
     * @return equipment_id - 数据ID
     */
    public Long getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设置数据ID
     *
     * @param equipmentId 数据ID
     */
    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
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
     * @return data_time
     */
    public Date getDataTime() {
        return dataTime;
    }

    /**
     * @param dataTime
     */
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 获取检测值
     *
     * @return integral_point - 检测值
     */
    public String getIntegralPoint() {
        return integralPoint;
    }

    /**
     * 设置检测值
     *
     * @param integralPoint 检测值
     */
    public void setIntegralPoint(String integralPoint) {
        this.integralPoint = integralPoint;
    }

    /**
     * @return value
     */
    public Float getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(Float value) {
        this.value = value;
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
}