package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.BaseCustomer;
import com.sitop.smart365.dataservice.service.BaseCustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/02/26.
*/
@RestController
@RequestMapping("/base/customer")
public class BaseCustomerController {
    @Resource
    private BaseCustomerService baseCustomerService;

    @PostMapping
    public Result add(@RequestBody BaseCustomer baseCustomer) {
        baseCustomerService.save(baseCustomer);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        baseCustomerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody BaseCustomer baseCustomer) {
        baseCustomerService.update(baseCustomer);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        BaseCustomer baseCustomer = baseCustomerService.findById(id);
        return ResultGenerator.genSuccessResult(baseCustomer);
    }


@GetMapping
public Result list(){
 List<BaseCustomer> list = baseCustomerService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
