package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.AlarmDataEnvItemMapper;
import com.sitop.smart365.dataservice.model.AlarmDataEnvItem;
import com.sitop.smart365.dataservice.service.AlarmDataEnvItemService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/07.
 */
@Service
@Transactional
public class AlarmDataEnvItemServiceImpl extends AbstractService<AlarmDataEnvItem> implements AlarmDataEnvItemService {
    @Resource
    private AlarmDataEnvItemMapper alarmDataEnvItemMapper;

}
