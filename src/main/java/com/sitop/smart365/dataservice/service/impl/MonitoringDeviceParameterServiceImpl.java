package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.MonitoringDeviceParameterMapper;
import com.sitop.smart365.dataservice.model.MonitoringDeviceParameter;
import com.sitop.smart365.dataservice.service.MonitoringDeviceParameterService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
@Service
@Transactional
public class MonitoringDeviceParameterServiceImpl extends AbstractService<MonitoringDeviceParameter> implements MonitoringDeviceParameterService {
    @Resource
    private MonitoringDeviceParameterMapper monitoringDeviceParameterMapper;

}
