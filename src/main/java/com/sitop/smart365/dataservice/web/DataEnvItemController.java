package com.sitop.smart365.dataservice.web;

import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.DataEnvItem;
import com.sitop.smart365.dataservice.service.DataEnvItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/09.
*/
@RestController
@RequestMapping("/data/env/item")
public class DataEnvItemController {
    @Resource
    private DataEnvItemService dataEnvItemService;

    @PostMapping
    public Result add(@RequestBody DataEnvItem dataEnvItem) {
        dataEnvItemService.save(dataEnvItem);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        dataEnvItemService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody DataEnvItem dataEnvItem) {
        dataEnvItemService.update(dataEnvItem);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        DataEnvItem dataEnvItem = dataEnvItemService.findById(id);
        return ResultGenerator.genSuccessResult(dataEnvItem);
    }


@GetMapping
public Result list(){
 List<DataEnvItem> list = dataEnvItemService.findAll();
        return ResultGenerator.genSuccessResult(list);
}
}
