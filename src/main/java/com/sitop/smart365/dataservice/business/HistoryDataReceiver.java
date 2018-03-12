package com.sitop.smart365.dataservice.business;

import com.alibaba.fastjson.JSON;
import com.sitop.smart365.dataservice.model.*;
import com.sitop.smart365.dataservice.service.*;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Component
public class HistoryDataReceiver {

    @Resource
    private MonitoringDeviceParameterService monitoringDeviceParameterService;
    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private BaseCustomerService baseCustomerService;
    @Resource
    private DataEnvService dataEnvService;
    @Resource
    private DataEnvItemService dataEnvItemService;

    @RabbitListener(queues = "Smart365_history_service")
    public void processData(byte[] data) {
        String message = new String(data, StandardCharsets.UTF_8);
        HistoryDataItem historyDataItem = JSON.parseObject(message, HistoryDataItem.class);
        System.out.println("HistoryDataReceiver: " + message);

        MonitoringDeviceParameter deviceParameter = monitoringDeviceParameterService.findById(historyDataItem.getParameterId());
        if (deviceParameter == null) {
            return;
        }
        DataEnv dataEnvResult = dataEnvService.findById(historyDataItem.getParameterId());
        if (dataEnvResult == null) {

            MonitoringDevice monitoringDevice = monitoringDeviceService.findById(deviceParameter.getDeviceId());
            if (monitoringDevice == null) {
                return;
            }
            BaseCustomer currentCustomer = baseCustomerService.findById(monitoringDevice.getCustomerId());
            if (currentCustomer == null) {
                return;
            }
            DataEnv dataEnv = new DataEnv();
            dataEnv.setAgentId(currentCustomer.getAgentId());
            dataEnv.setCustomerId(currentCustomer.getCustomerId());
            dataEnv.setSubstationId(Long.parseLong(monitoringDevice.getSubstationId()));
        } else {

        }

    }
}
