package com.sitop.smart365.dataservice.service;
import com.sitop.smart365.dataservice.model.MonitoringDeviceCommand;
import com.sitop.smart365.dataservice.core.Service;
import com.sitop.smart365.dataservice.model.MonitoringDeviceRef;
import com.sun.javafx.binding.StringFormatter;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
public interface MonitoringDeviceCommandService extends Service<MonitoringDeviceCommand> {
    List<MonitoringDeviceCommand> getMonitoringDeviceCommand(String deviceId);
}
