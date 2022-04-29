package com.study.config.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试feign接口：fegin接口消费方
 *
 * @author zhangpba
 */
@RestController
public class ConfigClientController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigClientController.class);
    /**
     * 测试get请求：消费方
     *
     * @param name 参数
     * @return
     */
    @Value("${foo}")
    String foo;

    @GetMapping(value = "/foo")
    public String hi() {
        return foo;
    }

}
