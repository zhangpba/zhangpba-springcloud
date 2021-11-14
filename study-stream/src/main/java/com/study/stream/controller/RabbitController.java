package com.study.stream.controller;

import com.study.stream.service.RabbitStreamClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * rabbit接口消费方
 *
 * @author zhangpba
 * @date 2021-11-05
 */
@Api(value = "rabbit接口消费方")
@RestController
public class RabbitController {
    private static final Logger logger = LoggerFactory.getLogger(RabbitController.class);

    @Autowired
    private RabbitStreamClient rabbitStreamClient;

    /**
     * 测试post请求：消费方
     *
     * @return
     */
    @ApiOperation(value = "生产数据")
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(String value) {
        rabbitStreamClient.output().send(MessageBuilder.withPayload(value).build());
        return "send sucesss!";
    }
}
