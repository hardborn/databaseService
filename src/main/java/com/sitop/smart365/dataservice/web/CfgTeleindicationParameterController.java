package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.CfgTeleindicationParameter;
import com.sitop.smart365.dataservice.service.CfgTeleindicationParameterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/29.
*/
@RestController
@RequestMapping("/cfg/teleindication/parameter")
public class CfgTeleindicationParameterController {
    @Resource
    private CfgTeleindicationParameterService cfgTeleindicationParameterService;

    @PostMapping
    public Result add(@RequestBody CfgTeleindicationParameter cfgTeleindicationParameter) {
        cfgTeleindicationParameterService.save(cfgTeleindicationParameter);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        cfgTeleindicationParameterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CfgTeleindicationParameter cfgTeleindicationParameter) {
        cfgTeleindicationParameterService.update(cfgTeleindicationParameter);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        CfgTeleindicationParameter cfgTeleindicationParameter = cfgTeleindicationParameterService.findById(id);
        return ResultGenerator.genSuccessResult(cfgTeleindicationParameter);
    }


@GetMapping
public Result list(){
 List<CfgTeleindicationParameter> list = cfgTeleindicationParameterService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
