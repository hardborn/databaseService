package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.DataEnvMapper;
import com.sitop.smart365.dataservice.model.DataEnv;
import com.sitop.smart365.dataservice.service.DataEnvService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/09.
 */
@Service
@Transactional
public class DataEnvServiceImpl extends AbstractService<DataEnv> implements DataEnvService {
    @Resource
    private DataEnvMapper dataEnvMapper;

}
