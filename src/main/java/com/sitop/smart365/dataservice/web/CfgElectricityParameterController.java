package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.CfgElectricityParameter;
import com.sitop.smart365.dataservice.service.CfgElectricityParameterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/25.
*/
@RestController
@RequestMapping("/cfg/electricity/parameter")
public class CfgElectricityParameterController {
    @Resource
    private CfgElectricityParameterService cfgElectricityParameterService;

    @PostMapping
    public Result add(@RequestBody CfgElectricityParameter cfgElectricityParameter) {
        cfgElectricityParameterService.save(cfgElectricityParameter);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        cfgElectricityParameterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CfgElectricityParameter cfgElectricityParameter) {
        cfgElectricityParameterService.update(cfgElectricityParameter);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        CfgElectricityParameter cfgElectricityParameter = cfgElectricityParameterService.findById(id);
        return ResultGenerator.genSuccessResult(cfgElectricityParameter);
    }


@GetMapping
public Result list(){
 List<CfgElectricityParameter> list = cfgElectricityParameterService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
