package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.MonitoringDeviceCommandMapper;
import com.sitop.smart365.dataservice.model.MonitoringDeviceCommand;
import com.sitop.smart365.dataservice.service.MonitoringDeviceCommandService;
import com.sitop.smart365.dataservice.core.AbstractService;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
@Service
@Transactional
public class MonitoringDeviceCommandServiceImpl extends AbstractService<MonitoringDeviceCommand> implements MonitoringDeviceCommandService {
    @Resource
    private MonitoringDeviceCommandMapper monitoringDeviceCommandMapper;

    public List<MonitoringDeviceCommand> getMonitoringDeviceCommand(String deviceId) {
        Condition condition = new Condition(MonitoringDeviceCommand.class);
        String conditionQuery = StringFormatter.format("device_id = '%s'", deviceId).getValue();
        condition.createCriteria().andCondition(conditionQuery);
        List<MonitoringDeviceCommand> deviceCommands = this.findByCondition(condition);
        return deviceCommands;
    }
}
