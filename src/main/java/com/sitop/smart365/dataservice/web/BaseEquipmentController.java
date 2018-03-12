package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.BaseEquipment;
import com.sitop.smart365.dataservice.service.BaseEquipmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/02/26.
*/
@RestController
@RequestMapping("/base/equipment")
public class BaseEquipmentController {
    @Resource
    private BaseEquipmentService baseEquipmentService;

    @PostMapping
    public Result add(@RequestBody BaseEquipment baseEquipment) {
        baseEquipmentService.save(baseEquipment);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        baseEquipmentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody BaseEquipment baseEquipment) {
        baseEquipmentService.update(baseEquipment);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        BaseEquipment baseEquipment = baseEquipmentService.findById(id);
        return ResultGenerator.genSuccessResult(baseEquipment);
    }


@GetMapping
public Result list(){
 List<BaseEquipment> list = baseEquipmentService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
