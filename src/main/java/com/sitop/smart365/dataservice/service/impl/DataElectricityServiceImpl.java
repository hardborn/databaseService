package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.DataElectricityMapper;
import com.sitop.smart365.dataservice.model.DataElectricity;
import com.sitop.smart365.dataservice.service.DataElectricityService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/22.
 */
@Service
@Transactional
public class DataElectricityServiceImpl extends AbstractService<DataElectricity> implements DataElectricityService {
    @Resource
    private DataElectricityMapper dataElectricityMapper;

}
