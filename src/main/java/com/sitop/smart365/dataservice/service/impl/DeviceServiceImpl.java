package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.DeviceMapper;
import com.sitop.smart365.dataservice.model.Device;
import com.sitop.smart365.dataservice.service.DeviceService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/25.
 */
@Service
@Transactional
public class DeviceServiceImpl extends AbstractService<Device> implements DeviceService {
    @Resource
    private DeviceMapper deviceMapper;

}
