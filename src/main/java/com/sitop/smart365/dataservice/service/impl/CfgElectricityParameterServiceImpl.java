package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.CfgElectricityParameterMapper;
import com.sitop.smart365.dataservice.model.CfgElectricityParameter;
import com.sitop.smart365.dataservice.service.CfgElectricityParameterService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
@Service
@Transactional
public class CfgElectricityParameterServiceImpl extends AbstractService<CfgElectricityParameter> implements CfgElectricityParameterService {
    @Resource
    private CfgElectricityParameterMapper cfgElectricityParameterMapper;

}
