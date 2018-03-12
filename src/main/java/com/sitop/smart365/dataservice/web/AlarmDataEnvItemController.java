package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.AlarmDataEnvItem;
import com.sitop.smart365.dataservice.service.AlarmDataEnvItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/07.
*/
@RestController
@RequestMapping("/alarm/data/env/item")
public class AlarmDataEnvItemController {
    @Resource
    private AlarmDataEnvItemService alarmDataEnvItemService;

    @PostMapping
    public Result add(@RequestBody AlarmDataEnvItem alarmDataEnvItem) {
        alarmDataEnvItemService.save(alarmDataEnvItem);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        alarmDataEnvItemService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody AlarmDataEnvItem alarmDataEnvItem) {
        alarmDataEnvItemService.update(alarmDataEnvItem);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        AlarmDataEnvItem alarmDataEnvItem = alarmDataEnvItemService.findById(id);
        return ResultGenerator.genSuccessResult(alarmDataEnvItem);
    }


@GetMapping
public Result list(){
 List<AlarmDataEnvItem> list = alarmDataEnvItemService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
