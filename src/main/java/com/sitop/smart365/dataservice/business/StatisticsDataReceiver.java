package com.sitop.smart365.dataservice.business;

import com.alibaba.fastjson.JSON;
import com.sitop.smart365.dataservice.model.BaseEquipment;
import com.sitop.smart365.dataservice.model.CfgEquipmentDeviceRef;
import com.sitop.smart365.dataservice.model.DataElectricity;
import com.sitop.smart365.dataservice.model.StatisticsInfo;
import com.sitop.smart365.dataservice.service.BaseEquipmentService;
import com.sitop.smart365.dataservice.service.DataElectricityService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class StatisticsDataReceiver {

    @Resource
    private DataElectricityService dataElectricityService;

    @Resource
    private BaseEquipmentService baseEquipmentService;

    @RabbitListener(queues = "Smart365_statistics_service")
    public void processMessage(String message) {
        try {
            StatisticsInfo statisticsInfo = JSON.parseObject(message, StatisticsInfo.class);
            if (statisticsInfo == null) {
                System.out.println(String.format("统计数据信息解析有误，无法进行数据存储！"));
                return;
            }

            Condition equipmentIdCondition = new Condition(BaseEquipment.class);
            String equipmentIdConditionQuery = String.format("equipment_id = '%s'", statisticsInfo.getEquipmentId());
            equipmentIdCondition.createCriteria().andCondition(equipmentIdConditionQuery);
            List<BaseEquipment> equipmentDeviceList = baseEquipmentService.findByCondition(equipmentIdCondition);

            if (equipmentDeviceList == null || equipmentDeviceList.size() == 0) {
                System.out.println(String.format("[%s]设备数据信息有误，无法进行数据存储！", statisticsInfo.getEquipmentId()));
                return;
            }

            BaseEquipment currentEquipment = equipmentDeviceList.get(0);
            DataElectricity dataElectricity = new DataElectricity();
            dataElectricity.setAgentId(currentEquipment.getAgentId());
            dataElectricity.setCustomerId(currentEquipment.getCustomerId());
            dataElectricity.setEquipmentId(currentEquipment.getEquipmentId());
            SimpleDateFormat dataFormat = new SimpleDateFormat("HH");
            String hourPart = dataFormat.format(statisticsInfo.getCurrentTime());
            dataElectricity.setIntegralPoint(hourPart);
            dataElectricity.setValue(Float.parseFloat(statisticsInfo.getCurrentValue()));
            dataElectricity.setParamType(statisticsInfo.getStatisticsType());

            dataElectricityService.save(dataElectricity);
        } catch (Exception ex) {
            System.out.println(String.format("存储电能统计数据发生错误：%s", ex.getMessage()));
        }
    }
}
