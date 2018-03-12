package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.CfgTelecommunicatingParameter;
import com.sitop.smart365.dataservice.service.CfgTelecommunicatingParameterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/25.
*/
@RestController
@RequestMapping("/cfg/telecommunicating/parameter")
public class CfgTelecommunicatingParameterController {
    @Resource
    private CfgTelecommunicatingParameterService cfgTelecommunicatingParameterService;

    @PostMapping
    public Result add(@RequestBody CfgTelecommunicatingParameter cfgTelecommunicatingParameter) {
        cfgTelecommunicatingParameterService.save(cfgTelecommunicatingParameter);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        cfgTelecommunicatingParameterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CfgTelecommunicatingParameter cfgTelecommunicatingParameter) {
        cfgTelecommunicatingParameterService.update(cfgTelecommunicatingParameter);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        CfgTelecommunicatingParameter cfgTelecommunicatingParameter = cfgTelecommunicatingParameterService.findById(id);
        return ResultGenerator.genSuccessResult(cfgTelecommunicatingParameter);
    }


@GetMapping
public Result list(){
 List<CfgTelecommunicatingParameter> list = cfgTelecommunicatingParameterService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
