package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.MonitoringDeviceParameter;
import com.sitop.smart365.dataservice.service.MonitoringDeviceParameterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/25.
*/
@RestController
@RequestMapping("/monitoring/device/parameter")
public class MonitoringDeviceParameterController {
    @Resource
    private MonitoringDeviceParameterService monitoringDeviceParameterService;

    @PostMapping
    public Result add(@RequestBody MonitoringDeviceParameter monitoringDeviceParameter) {
        monitoringDeviceParameterService.save(monitoringDeviceParameter);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        monitoringDeviceParameterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody MonitoringDeviceParameter monitoringDeviceParameter) {
        monitoringDeviceParameterService.update(monitoringDeviceParameter);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        MonitoringDeviceParameter monitoringDeviceParameter = monitoringDeviceParameterService.findById(id);
        return ResultGenerator.genSuccessResult(monitoringDeviceParameter);
    }


@GetMapping
public Result list(){
 List<MonitoringDeviceParameter> list = monitoringDeviceParameterService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
