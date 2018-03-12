package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.CfgTelemetryParameterMapper;
import com.sitop.smart365.dataservice.model.CfgTelemetryParameter;
import com.sitop.smart365.dataservice.service.CfgTelemetryParameterService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
@Service
@Transactional
public class CfgTelemetryParameterServiceImpl extends AbstractService<CfgTelemetryParameter> implements CfgTelemetryParameterService {
    @Resource
    private CfgTelemetryParameterMapper cfgTelemetryParameterMapper;

}
