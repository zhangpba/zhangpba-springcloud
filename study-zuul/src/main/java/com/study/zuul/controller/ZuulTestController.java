package com.study.zuul.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试网关
 *
 * @author zhangpba
 * @date 2021-06-01
 */
@RestController
public class ZuulTestController {
    private static final Logger logger = LoggerFactory.getLogger(ZuulTestController.class);

    /**
     * 测试get请求：消费方
     *
     * @param name 参数
     * @return
     */
    @RequestMapping(value = "/zuul/get", method = RequestMethod.GET)
    public String getFile(String name) {
        return "测试网关！";
    }

}
