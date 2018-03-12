package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.CfgTeleindicationParameterMapper;
import com.sitop.smart365.dataservice.model.CfgTeleindicationParameter;
import com.sitop.smart365.dataservice.service.CfgTeleindicationParameterService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/29.
 */
@Service
@Transactional
public class CfgTeleindicationParameterServiceImpl extends AbstractService<CfgTeleindicationParameter> implements CfgTeleindicationParameterService {
    @Resource
    private CfgTeleindicationParameterMapper cfgTeleindicationParameterMapper;

}
