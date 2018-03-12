package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.CfgEquipmentDeviceRefMapper;
import com.sitop.smart365.dataservice.model.CfgEquipmentDeviceRef;
import com.sitop.smart365.dataservice.service.CfgEquipmentDeviceRefService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/02/24.
 */
@Service
@Transactional
public class CfgEquipmentDeviceRefServiceImpl extends AbstractService<CfgEquipmentDeviceRef> implements CfgEquipmentDeviceRefService {
    @Resource
    private CfgEquipmentDeviceRefMapper cfgEquipmentDeviceRefMapper;

}
