package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.Device;
import com.sitop.smart365.dataservice.service.DeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/25.
*/
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Resource
    private DeviceService deviceService;

    @PostMapping
    public Result add(@RequestBody Device device) {
        deviceService.save(device);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        deviceService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Device device) {
        deviceService.update(device);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        Device device = deviceService.findById(id);
        return ResultGenerator.genSuccessResult(device);
    }


@GetMapping
public Result list(){
 List<Device> list = deviceService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
