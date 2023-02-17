package com.data.chain.kafkamsg.controller;

import com.data.chain.kafkamsg.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/8/8
 */
@Api(value = "消息控制类")
@RestController
@RequestMapping("/message")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private IMessageService messageService;

    /**
     * 发送消息
     *
     * @return
     */
    @ApiOperation(value = "发送消息")
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Object send(@ApiParam(name = "topic", value = "TOPIC", required = true) @RequestParam String topic,
                       @ApiParam(name = "message", value = "消息内容", required = true) @RequestParam String message) {
        messageService.send(topic, message);
        return "success!";
    }

    /**
     * 发送消息
     *
     * @return
     */
    @ApiOperation(value = "发送消息")
    @RequestMapping(value = "/sendCallBack", method = RequestMethod.GET)
    public Object sendCallBack(@ApiParam(name = "topic", value = "TOPIC", required = true) @RequestParam String topic,
                               @ApiParam(name = "message", value = "消息内容", required = true) @RequestParam String message) {
        messageService.sendCallBack(topic, message);
        return "success!";
    }
}
