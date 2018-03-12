package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.DataEnvItemMapper;
import com.sitop.smart365.dataservice.model.DataEnvItem;
import com.sitop.smart365.dataservice.service.DataEnvItemService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/09.
 */
@Service
@Transactional
public class DataEnvItemServiceImpl extends AbstractService<DataEnvItem> implements DataEnvItemService {
    @Resource
    private DataEnvItemMapper dataEnvItemMapper;

}
