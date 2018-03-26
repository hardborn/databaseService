package com.sitop.smart365.dataservice.business;

import com.alibaba.fastjson.JSON;
import com.sitop.smart365.dataservice.model.AlarmDataEnv;
import com.sitop.smart365.dataservice.model.AlarmDataEnvItem;
import com.sitop.smart365.dataservice.model.AlarmInfo;
import com.sitop.smart365.dataservice.model.MonitoringDeviceRef;
import com.sitop.smart365.dataservice.service.AlarmDataEnvItemService;
import com.sitop.smart365.dataservice.service.AlarmDataEnvService;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AlarmReceiver {

    @Resource
    private AlarmDataEnvService alarmDataEnvService;
    @Resource
    private AlarmDataEnvItemService alarmDataEnvItemService;

    @RabbitListener(queues = "Smart365_alarm_service")
    public void process(String message) {
        AlarmInfo alarmInfo = JSON.parseObject(message, AlarmInfo.class);
        System.out.println("AlarmReceiver: " + message);

        Condition condition = new Condition(AlarmDataEnv.class);
        String conditionQuery = String.format("equipment_id = '%s' AND  param_id = '%s' AND handle_state <> 2", alarmInfo.getEquipmentId(), alarmInfo.getParamId());
        condition.createCriteria().andCondition(conditionQuery);
        List<AlarmDataEnv> alarmDataEnvs = alarmDataEnvService.findByCondition(condition);

        String alarmUid = UUID.randomUUID().toString();
        //Date currentDate = new Date();
        AlarmDataEnvItem alarmDataEnvItem = new AlarmDataEnvItem();

        if (alarmDataEnvs == null || alarmDataEnvs.size() == 0) {
            AlarmDataEnv alarmDataEnv = new AlarmDataEnv();
            alarmDataEnv.setAgentId(Long.parseLong(alarmInfo.getAgentId()));
            alarmDataEnv.setCustomerId(Long.parseLong(alarmInfo.getCustomerId()));
            alarmDataEnv.setSubstationId(Long.parseLong(alarmInfo.getSubstationId()));
            alarmDataEnv.setEquipmentId(Long.parseLong(alarmInfo.getEquipmentId()));
            alarmDataEnv.setParamId(alarmInfo.getParamId());
            alarmDataEnv.setAlarmCount(1);
            alarmDataEnv.setAlarmContent(alarmInfo.getAlarmContent());
            alarmDataEnv.setAlarmLevel(Integer.parseInt(alarmInfo.getAlarmLevel()));
            alarmDataEnv.setAlarmValue(Float.parseFloat(alarmInfo.getAlarmValue()));
            alarmDataEnv.setAlarmUid(alarmUid);
            alarmDataEnv.setAlarmTime(alarmInfo.getAlarmTime());
            alarmDataEnv.setConfirmState(0);
            alarmDataEnv.setHandleState(0);
            alarmDataEnvService.save(alarmDataEnv);
            alarmDataEnvItem.setAlarmUid(alarmUid);
        } else {
            AlarmDataEnv currentAlarmDataEnv = alarmDataEnvs.get(0);
            currentAlarmDataEnv.setAlarmCount(currentAlarmDataEnv.getAlarmCount()+1);
            currentAlarmDataEnv.setAlarmValue(Float.parseFloat(alarmInfo.getAlarmValue()));
            currentAlarmDataEnv.setAlarmTime(new Date());
            currentAlarmDataEnv.setAlarmContent(alarmInfo.getAlarmContent());
            if (currentAlarmDataEnv.getAlarmLevel() > Integer.parseInt(alarmInfo.getAlarmLevel())){
                currentAlarmDataEnv.setAlarmLevel(Integer.parseInt(alarmInfo.getAlarmLevel()));
            }
            alarmDataEnvService.update(currentAlarmDataEnv);
            alarmDataEnvItem.setAlarmUid(currentAlarmDataEnv.getAlarmUid());
        }

        alarmDataEnvItem.setAgentId(Long.parseLong(alarmInfo.getAgentId()));
        alarmDataEnvItem.setCustomerId(Long.parseLong(alarmInfo.getCustomerId()));
        alarmDataEnvItem.setSubstationId(Long.parseLong(alarmInfo.getSubstationId()));
        alarmDataEnvItem.setEquipmentId(Long.parseLong(alarmInfo.getEquipmentId()));
        alarmDataEnvItem.setParamId(alarmInfo.getParamId());
        alarmDataEnvItem.setAlarmContent(alarmInfo.getAlarmContent());
        alarmDataEnvItem.setAlarmLevel(Integer.parseInt(alarmInfo.getAlarmLevel()));
        alarmDataEnvItem.setAlarmValue(Float.parseFloat(alarmInfo.getAlarmValue()));
        alarmDataEnvItem.setAlarmTime(alarmInfo.getAlarmTime());
        alarmDataEnvItem.setAlarmItemUid(UUID.randomUUID().toString());
        alarmDataEnvItem.setConfirmState(0);
        alarmDataEnvItem.setHandleState(0);
        alarmDataEnvItemService.save(alarmDataEnvItem);
    }
}
