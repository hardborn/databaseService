package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.DeviceTemplate;
import com.sitop.smart365.dataservice.service.DeviceTemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/25.
*/
@RestController
@RequestMapping("/device/template")
public class DeviceTemplateController {
    @Resource
    private DeviceTemplateService deviceTemplateService;

    @PostMapping
    public Result add(@RequestBody DeviceTemplate deviceTemplate) {
        deviceTemplateService.save(deviceTemplate);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        deviceTemplateService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody DeviceTemplate deviceTemplate) {
        deviceTemplateService.update(deviceTemplate);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        DeviceTemplate deviceTemplate = deviceTemplateService.findById(id);
        return ResultGenerator.genSuccessResult(deviceTemplate);
    }


@GetMapping
public Result list(){
 List<DeviceTemplate> list = deviceTemplateService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
