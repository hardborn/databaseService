package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.MonitoringDeviceCommand;
import com.sitop.smart365.dataservice.service.MonitoringDeviceCommandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/25.
*/
@RestController
@RequestMapping("/monitoring/device/command")
public class MonitoringDeviceCommandController {
    @Resource
    private MonitoringDeviceCommandService monitoringDeviceCommandService;

    @PostMapping
    public Result add(@RequestBody MonitoringDeviceCommand monitoringDeviceCommand) {
        monitoringDeviceCommandService.save(monitoringDeviceCommand);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        monitoringDeviceCommandService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody MonitoringDeviceCommand monitoringDeviceCommand) {
        monitoringDeviceCommandService.update(monitoringDeviceCommand);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        MonitoringDeviceCommand monitoringDeviceCommand = monitoringDeviceCommandService.getMonitoringDeviceCommand(id).get(0);
        return ResultGenerator.genSuccessResult(monitoringDeviceCommand);
    }



@GetMapping
public Result list(){
 List<MonitoringDeviceCommand> list = monitoringDeviceCommandService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
