package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.BaseEquipmentMapper;
import com.sitop.smart365.dataservice.model.BaseEquipment;
import com.sitop.smart365.dataservice.service.BaseEquipmentService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/02/26.
 */
@Service
@Transactional
public class BaseEquipmentServiceImpl extends AbstractService<BaseEquipment> implements BaseEquipmentService {
    @Resource
    private BaseEquipmentMapper baseEquipmentMapper;

}
