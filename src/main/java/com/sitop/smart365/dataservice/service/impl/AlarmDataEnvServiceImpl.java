package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.AlarmDataEnvMapper;
import com.sitop.smart365.dataservice.model.AlarmDataEnv;
import com.sitop.smart365.dataservice.service.AlarmDataEnvService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/07.
 */
@Service
@Transactional
public class AlarmDataEnvServiceImpl extends AbstractService<AlarmDataEnv> implements AlarmDataEnvService {
    @Resource
    private AlarmDataEnvMapper alarmDataEnvMapper;

}
