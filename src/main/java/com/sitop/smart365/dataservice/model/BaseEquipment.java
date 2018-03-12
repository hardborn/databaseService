package com.sitop.smart365.dataservice.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_equipment")
public class BaseEquipment {
    @Id
    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "equipment_type_id")
    private Integer equipmentTypeId;

    @Column(name = "voltage_level_code")
    private String voltageLevelCode;

    @Column(name = "manufacturer_id")
    private Integer manufacturerId;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "start_use_time")
    private Date startUseTime;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "grid_id")
    private Long gridId;

    @Column(name = "line_id")
    private Long lineId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "substation_id")
    private Long substationId;

    @Column(name = "bay_id")
    private Long bayId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_modify_time")
    private Date lastModifyTime;

    @Column(name = "is_deleted")
    private Integer isDeleted;

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
     * @return equipment_name
     */
    public String getEquipmentName() {
        return equipmentName;
    }

    /**
     * @param equipmentName
     */
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return equipment_type_id
     */
    public Integer getEquipmentTypeId() {
        return equipmentTypeId;
    }

    /**
     * @param equipmentTypeId
     */
    public void setEquipmentTypeId(Integer equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    /**
     * @return voltage_level_code
     */
    public String getVoltageLevelCode() {
        return voltageLevelCode;
    }

    /**
     * @param voltageLevelCode
     */
    public void setVoltageLevelCode(String voltageLevelCode) {
        this.voltageLevelCode = voltageLevelCode;
    }

    /**
     * @return manufacturer_id
     */
    public Integer getManufacturerId() {
        return manufacturerId;
    }

    /**
     * @param manufacturerId
     */
    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * @return serial_number
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return start_use_time
     */
    public Date getStartUseTime() {
        return startUseTime;
    }

    /**
     * @param startUseTime
     */
    public void setStartUseTime(Date startUseTime) {
        this.startUseTime = startUseTime;
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
     * @return grid_id
     */
    public Long getGridId() {
        return gridId;
    }

    /**
     * @param gridId
     */
    public void setGridId(Long gridId) {
        this.gridId = gridId;
    }

    /**
     * @return line_id
     */
    public Long getLineId() {
        return lineId;
    }

    /**
     * @param lineId
     */
    public void setLineId(Long lineId) {
        this.lineId = lineId;
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
     * @return bay_id
     */
    public Long getBayId() {
        return bayId;
    }

    /**
     * @param bayId
     */
    public void setBayId(Long bayId) {
        this.bayId = bayId;
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
     * @return last_modify_time
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * @param lastModifyTime
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * @return is_deleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}