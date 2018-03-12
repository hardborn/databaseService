package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.MonitoringDeviceRef;
import com.sitop.smart365.dataservice.service.MonitoringDeviceRefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/26.
*/
@RestController
@RequestMapping("/monitoring/device/ref")
public class MonitoringDeviceRefController {
    @Resource
    private MonitoringDeviceRefService monitoringDeviceRefService;

    @PostMapping
    public Result add(@RequestBody MonitoringDeviceRef monitoringDeviceRef) {
        monitoringDeviceRefService.save(monitoringDeviceRef);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        monitoringDeviceRefService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody MonitoringDeviceRef monitoringDeviceRef) {
        monitoringDeviceRefService.update(monitoringDeviceRef);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        MonitoringDeviceRef monitoringDeviceRef = monitoringDeviceRefService.findById(id);
        return ResultGenerator.genSuccessResult(monitoringDeviceRef);
    }


@GetMapping
public Result list(){
 List<MonitoringDeviceRef> list = monitoringDeviceRefService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
