package com.study.file.controller;

import com.study.file.service.FileService;
import com.study.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试feign接口：fegin接口服务提供方
 *
 * @author zhangpba
 */
@RestController
public class FeginTestController {
    private static final Logger logger = LoggerFactory.getLogger(FeginTestController.class);

    @Autowired
    private FileService fileService;

    /**
     * 测试feign接口的get请求：服务提供方
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/getFileHost", method = RequestMethod.GET)
    public String getFileHost(@RequestParam("name") String name) {
        logger.info("进入feign服务提供者 FeginTestController：getFileHost");
        return fileService.getFileHost(name);
    }

    /**
     * 测试feign接口的post请求：服务提供方
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/postFileHost", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String postFileHost(@RequestBody User user) {
        logger.info("进入feign服务提供者 FeginTestController：postFileHost");
        return fileService.postFileHost(user);
    }
}
