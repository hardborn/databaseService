package com.sitop.smart365.dataservice.service.impl;

import com.sitop.smart365.dataservice.dao.BaseCustomerMapper;
import com.sitop.smart365.dataservice.model.BaseCustomer;
import com.sitop.smart365.dataservice.service.BaseCustomerService;
import com.sitop.smart365.dataservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/02/26.
 */
@Service
@Transactional
public class BaseCustomerServiceImpl extends AbstractService<BaseCustomer> implements BaseCustomerService {
    @Resource
    private BaseCustomerMapper baseCustomerMapper;

}
