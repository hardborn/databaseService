package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.CfgEquipmentDeviceRef;
import com.sitop.smart365.dataservice.service.CfgEquipmentDeviceRefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/02/24.
*/
@RestController
@RequestMapping("/cfg/equipment/device/ref")
public class CfgEquipmentDeviceRefController {
    @Resource
    private CfgEquipmentDeviceRefService cfgEquipmentDeviceRefService;

    @PostMapping
    public Result add(@RequestBody CfgEquipmentDeviceRef cfgEquipmentDeviceRef) {
        cfgEquipmentDeviceRefService.save(cfgEquipmentDeviceRef);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        cfgEquipmentDeviceRefService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CfgEquipmentDeviceRef cfgEquipmentDeviceRef) {
        cfgEquipmentDeviceRefService.update(cfgEquipmentDeviceRef);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        CfgEquipmentDeviceRef cfgEquipmentDeviceRef = cfgEquipmentDeviceRefService.findById(id);
        return ResultGenerator.genSuccessResult(cfgEquipmentDeviceRef);
    }


@GetMapping
public Result list(){
 List<CfgEquipmentDeviceRef> list = cfgEquipmentDeviceRefService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
