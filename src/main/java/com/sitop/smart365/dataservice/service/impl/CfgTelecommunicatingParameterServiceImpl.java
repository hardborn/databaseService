package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.CfgTelecommunicatingParameterMapper;
import com.sitop.smart365.dataservice.model.CfgTelecommunicatingParameter;
import com.sitop.smart365.dataservice.service.CfgTelecommunicatingParameterService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
@Service
@Transactional
public class CfgTelecommunicatingParameterServiceImpl extends AbstractService<CfgTelecommunicatingParameter> implements CfgTelecommunicatingParameterService {
    @Resource
    private CfgTelecommunicatingParameterMapper cfgTelecommunicatingParameterMapper;

}
