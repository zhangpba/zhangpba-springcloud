package com.study.config.controller;

import com.study.config.mapper.ConfigSeverMapper;
import com.study.config.model.ConfigInfo;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试ConfigSever
 *
 * @author zhangpba
 * @date 2021-08-22
 */
@Api(value = "配置中心", tags = "配置中心")
@RestController
public class ConfigSeverController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigSeverController.class);

//    @Autowired
//    private ConfigSeverMapper configSeverMapper;

    /**
     * 测试get请求：消费方
     *
     * @param name 参数
     * @return
     */
    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
    public ConfigInfo getConfig(String name) {
        logger.info("study-user服务，参数:{}", name);
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setId(1);
//        ConfigInfo result = configSeverMapper.getConfigInfo(configInfo);
        return configInfo;
    }


    @Autowired
    private ConfigSeverMapper configSeverMapper;

    /**
     * 增加配置信息
     *
     * @return
     */
    @ApiOperation(value = "增加配置信息")
    @GetMapping(value = "/config")
    public ResponseMessage config(@ApiParam(name = "key", value = "key", required = true) @RequestParam String key,
                                  @ApiParam(name = "value", value = "value", required = true) @RequestParam String value,
                                  @ApiParam(name = "application", value = "application", required = true) @RequestParam String application,
                                  @ApiParam(name = "profile", value = "profile", required = true) @RequestParam String profile,
                                  @ApiParam(name = "lable", value = "lable", required = true) @RequestParam String lable
    ) {
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setApplication(application);
        configInfo.setKey(key);
        configInfo.setValue(value);
        configInfo.setProfile(profile);
        configInfo.setCreateDate(new Date());
        configSeverMapper.addConfigInfo(configInfo);
        return ResponseMessage.success();
    }

}
