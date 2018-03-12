package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.DeviceTemplateMapper;
import com.sitop.smart365.dataservice.model.DeviceTemplate;
import com.sitop.smart365.dataservice.service.DeviceTemplateService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
@Service
@Transactional
public class DeviceTemplateServiceImpl extends AbstractService<DeviceTemplate> implements DeviceTemplateService {
    @Resource
    private DeviceTemplateMapper deviceTemplateMapper;

}
