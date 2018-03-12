package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.MonitoringDeviceRefMapper;
import com.sitop.smart365.dataservice.model.MonitoringDeviceRef;
import com.sitop.smart365.dataservice.service.MonitoringDeviceRefService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/26.
 */
@Service
@Transactional
public class MonitoringDeviceRefServiceImpl extends AbstractService<MonitoringDeviceRef> implements MonitoringDeviceRefService {
    @Resource
    private MonitoringDeviceRefMapper monitoringDeviceRefMapper;

}
