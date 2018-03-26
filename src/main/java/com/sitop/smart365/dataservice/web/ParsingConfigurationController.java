package com.sitop.smart365.dataservice.web;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.sitop.smart365.dataservice.core.Result;
import com.sitop.smart365.dataservice.core.ResultGenerator;
import com.sitop.smart365.dataservice.model.ParsingConfiguration;
import com.sitop.smart365.dataservice.service.ParsingConfigurationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/parsingconfigs")
public class ParsingConfigurationController {
    @Resource
    private ParsingConfigurationService parsingConfigurationService;

    private static final Log LOG = LogFactory.getLog(ParsingConfigurationController.class);

    @GetMapping
    @ResponseBody
    public Result list() {
        List<ParsingConfiguration> parsingConfigurations = parsingConfigurationService.getAllParsingConfigs();
        return ResultGenerator.genSuccessResult(parsingConfigurations );
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result getConfig(@PathVariable String id) {
        LOG.info("[" + id + "]" + " 请求数据解析配置信息");
        ParsingConfiguration parsingConfiguration = parsingConfigurationService.getParsingConfig(id);
        if (parsingConfiguration != null) {
            return ResultGenerator.genSuccessResult(parsingConfiguration);
        } else {
            LOG.warn("[" + id + "]" + " 获取数据解析配置异常");
            return null;
        }
    }
}

