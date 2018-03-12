package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.MonitoringDevice;
import com.sitop.smart365.dataservice.service.MonitoringDeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/09.
*/
@RestController
@RequestMapping("/monitoring/device")
public class MonitoringDeviceController {
    @Resource
    private MonitoringDeviceService monitoringDeviceService;

    @PostMapping
    public Result add(@RequestBody MonitoringDevice monitoringDevice) {
        monitoringDeviceService.save(monitoringDevice);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        monitoringDeviceService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody MonitoringDevice monitoringDevice) {
        monitoringDeviceService.update(monitoringDevice);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        MonitoringDevice monitoringDevice = monitoringDeviceService.findById(id);
        return ResultGenerator.genSuccessResult(monitoringDevice);
    }


@GetMapping
public Result list(){
 List<MonitoringDevice> list = monitoringDeviceService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
