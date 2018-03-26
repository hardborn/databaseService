package com.sitop.smart365.dataservice.business;

import com.alibaba.fastjson.JSON;
import com.sitop.smart365.dataservice.model.*;
import com.sitop.smart365.dataservice.service.*;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

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
    @Resource
    private CfgEquipmentDeviceRefService cfgEquipmentDeviceRefService;
    @Resource
    private BaseEquipmentService baseEquipmentService;

    @RabbitListener(queues = "Smart365_history_service")
    public void processData(String message) {
//        System.out.println("HistoryDataReceiver: " + message);
        HistoryDataItem historyDataItem = JSON.parseObject(message, HistoryDataItem.class);

        Condition equipmentCondition = new Condition(CfgEquipmentDeviceRef.class);
        String equipmentConditionQuery = String.format("param_id = '%s'", historyDataItem.getParameterId());
        equipmentCondition.createCriteria().andCondition(equipmentConditionQuery);
        List<CfgEquipmentDeviceRef> equipmentDeviceRefList = cfgEquipmentDeviceRefService.findByCondition(equipmentCondition);
        CfgEquipmentDeviceRef equipmentDeviceRef = equipmentDeviceRefList == null || equipmentDeviceRefList.size() == 0 ? null : equipmentDeviceRefList.get(0);
        if (equipmentDeviceRef == null) {
            System.out.println(String.format("[%s]该数据未关联用户设备，不进行数据存储！",historyDataItem.getParameterId()));
            return;
        }
        DataEnvItem dataEnvItem = new DataEnvItem();


        Condition equipmentIdCondition = new Condition(BaseEquipment.class);
        String equipmentIdConditionQuery = String.format("equipment_id = '%s'",equipmentDeviceRef.getEquipmentId().toString());
        equipmentIdCondition.createCriteria().andCondition(equipmentIdConditionQuery);
        List<BaseEquipment> baseEquipments = baseEquipmentService.findByCondition(equipmentIdCondition);
        if (baseEquipments == null || baseEquipments.size() == 0) {
            System.out.println(String.format("[%s]该数据所关联的用户设备[%s]数据不一致，数据无法存储！",historyDataItem.getParameterId(),equipmentDeviceRef.getEquipmentId()));
            return;
        }
        BaseEquipment baseEquipment = baseEquipments.get(0);
        Condition customerIdCondition = new Condition(BaseCustomer.class);
        String customerIdConditionQuery = String.format("customer_id = '%s'",baseEquipment.getCustomerId().toString());
        customerIdCondition.createCriteria().andCondition(customerIdConditionQuery);
        List<BaseCustomer> currentCustomers = baseCustomerService.findByCondition(customerIdCondition);
        if (currentCustomers == null || currentCustomers.size() == 0) {
            System.out.println(String.format("[%s]该数据所关联的用户设备[%s]无归属客户信息，数据无法存储！",historyDataItem.getParameterId(),equipmentDeviceRef.getEquipmentId()));
            return;
        }

        BaseCustomer currentCustomer = currentCustomers.get(0);
        Condition dataEnvCondition = new Condition(DataEnv.class);
        String dataEnvConditionQuery = String.format("equipment_id = '%s' AND create_time = '%s'", equipmentDeviceRef.getEquipmentId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(historyDataItem.getCurrentTime()));
        dataEnvCondition.createCriteria().andCondition(dataEnvConditionQuery);
        List<DataEnv> dataEnvResult = dataEnvService.findByCondition(dataEnvCondition);
        DataEnv currentDataEnv = dataEnvResult == null || dataEnvResult.size() == 0 ? null : dataEnvResult.get(0);

        if (currentDataEnv == null) {
            String uuid = UUID.randomUUID().toString();

            DataEnv dataEnv = new DataEnv();
            dataEnv.setDataUid(uuid);
            dataEnv.setAgentId(currentCustomer.getAgentId());
            dataEnv.setCustomerId(currentCustomer.getCustomerId());
            dataEnv.setSubstationId(Long.parseLong(baseEquipment.getSubstationId().toString()));
            dataEnv.setCreateTime(historyDataItem.getCurrentTime());
            dataEnv.setEquipmentId(baseEquipment.getEquipmentId());
            dataEnv.setIsAlarm(Integer.parseInt(historyDataItem.getIsAlarm()));
            dataEnvService.save(dataEnv);
            dataEnvItem.setDataUid(uuid);
            dataEnvItem.setDataItemUid(UUID.randomUUID().toString());
            dataEnvItem.setPointUid(historyDataItem.getParameterId());
            dataEnvItem.setValue(historyDataItem.getParameterValue());
            dataEnvItem.setIsAlarm(Integer.parseInt(historyDataItem.getIsAlarm()));
            dataEnvItem.setUpLimit(historyDataItem.getUpLimit());
            dataEnvItem.setDownLimit(historyDataItem.getDownLimit());
            dataEnvItem.setAgentId(currentCustomer.getAgentId());
            dataEnvItem.setCustomerId(currentCustomer.getCustomerId());
            dataEnvItem.setSubstationId(Long.parseLong(baseEquipment.getSubstationId().toString()));
            dataEnvItem.setCreateTime(historyDataItem.getCurrentTime());
            dataEnvItem.setEquipmentId(baseEquipment.getEquipmentId());
        } else {
            if (currentDataEnv.getIsAlarm() == null || (currentDataEnv.getIsAlarm().toString() != historyDataItem.getIsAlarm() && historyDataItem.getIsAlarm() == "1" )){
                currentDataEnv.setIsAlarm(Integer.parseInt(historyDataItem.getIsAlarm()));
                dataEnvService.update(currentDataEnv);
            }
            dataEnvItem.setDataUid(currentDataEnv.getDataUid());
            dataEnvItem.setDataItemUid(UUID.randomUUID().toString());
            dataEnvItem.setPointUid(historyDataItem.getParameterId());
            dataEnvItem.setValue(historyDataItem.getParameterValue());
            dataEnvItem.setIsAlarm(Integer.parseInt(historyDataItem.getIsAlarm()));
            dataEnvItem.setUpLimit(historyDataItem.getUpLimit());
            dataEnvItem.setDownLimit(historyDataItem.getDownLimit());
            dataEnvItem.setAgentId(currentCustomer.getAgentId());
            dataEnvItem.setCustomerId(currentCustomer.getCustomerId());
            dataEnvItem.setSubstationId(Long.parseLong(baseEquipment.getSubstationId().toString()));
            dataEnvItem.setCreateTime(historyDataItem.getCurrentTime());
            dataEnvItem.setEquipmentId(baseEquipment.getEquipmentId());
        }
        dataEnvItemService.save(dataEnvItem);
    }
}
