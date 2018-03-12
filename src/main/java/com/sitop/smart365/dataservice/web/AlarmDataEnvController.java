package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.AlarmDataEnv;
import com.sitop.smart365.dataservice.service.AlarmDataEnvService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/07.
*/
@RestController
@RequestMapping("/alarm/data/env")
public class AlarmDataEnvController {
    @Resource
    private AlarmDataEnvService alarmDataEnvService;

    @PostMapping
    public Result add(@RequestBody AlarmDataEnv alarmDataEnv) {
        alarmDataEnvService.save(alarmDataEnv);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        alarmDataEnvService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody AlarmDataEnv alarmDataEnv) {
        alarmDataEnvService.update(alarmDataEnv);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        AlarmDataEnv alarmDataEnv = alarmDataEnvService.findById(id);
        return ResultGenerator.genSuccessResult(alarmDataEnv);
    }


@GetMapping
public Result list(){
 List<AlarmDataEnv> list = alarmDataEnvService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
