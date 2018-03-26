package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.DataElectricity;
import com.sitop.smart365.dataservice.service.DataElectricityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/22.
*/
@RestController
@RequestMapping("/data/electricity")
public class DataElectricityController {
    @Resource
    private DataElectricityService dataElectricityService;

    @PostMapping
    public Result add(@RequestBody DataElectricity dataElectricity) {
        dataElectricityService.save(dataElectricity);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        dataElectricityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody DataElectricity dataElectricity) {
        dataElectricityService.update(dataElectricity);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        DataElectricity dataElectricity = dataElectricityService.findById(id);
        return ResultGenerator.genSuccessResult(dataElectricity);
    }


@GetMapping
public Result list(){
 List<DataElectricity> list = dataElectricityService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
