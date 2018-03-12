package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.CfgTelemetryParameter;
import com.sitop.smart365.dataservice.service.CfgTelemetryParameterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/25.
*/
@RestController
@RequestMapping("/cfg/telemetry/parameter")
public class CfgTelemetryParameterController {
    @Resource
    private CfgTelemetryParameterService cfgTelemetryParameterService;

    @PostMapping
    public Result add(@RequestBody CfgTelemetryParameter cfgTelemetryParameter) {
        cfgTelemetryParameterService.save(cfgTelemetryParameter);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        cfgTelemetryParameterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CfgTelemetryParameter cfgTelemetryParameter) {
        cfgTelemetryParameterService.update(cfgTelemetryParameter);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        CfgTelemetryParameter cfgTelemetryParameter = cfgTelemetryParameterService.findById(id);
        return ResultGenerator.genSuccessResult(cfgTelemetryParameter);
    }


@GetMapping
public Result list(){
 List<CfgTelemetryParameter> list = cfgTelemetryParameterService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
