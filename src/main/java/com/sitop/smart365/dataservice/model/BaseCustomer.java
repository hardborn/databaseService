package com.sitop.smart365.dataservice.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_customer")
public class BaseCustomer {
    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_introduction")
    private String customerIntroduction;

    @Column(name = "region_id")
    private Integer regionId;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "grid_id")
    private Long gridId;

    @Column(name = "line_id")
    private Long lineId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_modify_time")
    private Date lastModifyTime;

    @Column(name = "is_deleted")
    private Integer isDeleted;

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
     * @return customer_name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return customer_address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return customer_introduction
     */
    public String getCustomerIntroduction() {
        return customerIntroduction;
    }

    /**
     * @param customerIntroduction
     */
    public void setCustomerIntroduction(String customerIntroduction) {
        this.customerIntroduction = customerIntroduction;
    }

    /**
     * @return region_id
     */
    public Integer getRegionId() {
        return regionId;
    }

    /**
     * @param regionId
     */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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