package com.sitop.smart365.dataservice.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;


public class HistoryDataItem {
    @JSONField(name = "paramId")
    private String parameterId;
    @JSONField(name = "currentValue")
    private String parameterValue;
    @JSONField(name = "IsAlarm")
    private String isAlarm;
    @JSONField(name = "currentTime")
    private Date currentTime;
    @JSONField(name = "upLimit")
    private double upLimit;
    @JSONField(name = "downLimit")
    private double downLimit;

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }


    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(String isAlarm) {
        this.isAlarm = isAlarm;
    }

    public double getDownLimit() {
        return downLimit;
    }

    public void setDownLimit(double downLimit) {
        this.downLimit = downLimit;
    }

    public double getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(double upLimit) {
        this.upLimit = upLimit;
    }
}
