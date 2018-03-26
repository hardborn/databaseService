package com.sitop.smart365.dataservice.web;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.DataProcessingConfiguration;
import com.sitop.smart365.dataservice.model.ParsingConfiguration;
import com.sitop.smart365.dataservice.service.DataProcessingConfigurationService;
import com.sitop.smart365.dataservice.service.ParsingConfigurationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/dataprocessingconfigs")
public class DataProcessingConfigController {
     @Resource
    private DataProcessingConfigurationService dataProcessingConfigurationService;

    private static final Log LOG = LogFactory.getLog(ParsingConfigurationController.class);

    @GetMapping
    @ResponseBody
    public Result list() {
        List<DataProcessingConfiguration> dataProcessingConfigurations = dataProcessingConfigurationService.getAllDataProcessingConfigs();
        return ResultGenerator.genSuccessResult(dataProcessingConfigurations);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result getConfig(@PathVariable String id){
        LOG.info("[" + id + "]" + " 请求数据处理配置信息");
        DataProcessingConfiguration dataProcessingConfiguration = dataProcessingConfigurationService.getDataProcessingConfigs(id);
        if (dataProcessingConfiguration != null) {
            return ResultGenerator.genSuccessResult(dataProcessingConfiguration);
        } else {
            LOG.warn("[" + id + "]" + " 获取数据处理配置异常");
            return  null;
        }
    }
}
