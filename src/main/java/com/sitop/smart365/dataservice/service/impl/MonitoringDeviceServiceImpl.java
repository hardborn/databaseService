package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.MonitoringDeviceMapper;
import com.sitop.smart365.dataservice.model.MonitoringDevice;
import com.sitop.smart365.dataservice.service.MonitoringDeviceService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/09.
 */
@Service
@Transactional
public class MonitoringDeviceServiceImpl extends AbstractService<MonitoringDevice> implements MonitoringDeviceService {
    @Resource
    private MonitoringDeviceMapper monitoringDeviceMapper;

}
