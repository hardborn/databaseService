package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.DataEnv;
import com.sitop.smart365.dataservice.service.DataEnvService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/09.
*/
@RestController
@RequestMapping("/data/env")
public class DataEnvController {
    @Resource
    private DataEnvService dataEnvService;

    @PostMapping
    public Result add(@RequestBody DataEnv dataEnv) {
        dataEnvService.save(dataEnv);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        dataEnvService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody DataEnv dataEnv) {
        dataEnvService.update(dataEnv);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        DataEnv dataEnv = dataEnvService.findById(id);
        return ResultGenerator.genSuccessResult(dataEnv);
    }


@GetMapping
public Result list(){
 List<DataEnv> list = dataEnvService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
