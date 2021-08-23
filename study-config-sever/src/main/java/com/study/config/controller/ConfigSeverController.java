package com.study.config.controller;

import com.study.config.mapper.ConfigSeverMapper;
import com.study.config.model.ConfigInfo;
import com.study.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试ConfigSever
 *
 * @author zhangpba
 * @date 2021-08-22
 */
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

}
