package com.study.user.controller;

import com.study.starter.vo.user.User;
import com.study.user.feign.FileServiceFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试feign接口：fegin接口消费方
 *
 * @author zhangpba
 */
@Api(value = "fegin接口消费方")
@RestController
public class FeignTestController {
    private static final Logger logger = LoggerFactory.getLogger(FeignTestController.class);

    @Autowired
    private FileServiceFeign fileServiceFeign;

    /**
     * 测试post请求：消费方
     *
     * @param user 用户参数
     * @return
     */
    @ApiOperation(value = "测试post请求：消费方")
    @RequestMapping(value = "/client/postFile", method = RequestMethod.POST)
    public String postFile(User user) {
        logger.info("study-user服务，参数:{}", user);
        return fileServiceFeign.postFileHost(user);
    }

    /**
     * 测试get请求：消费方
     *
     * @param name 参数
     * @return
     */
    @ApiOperation(value = "测试get请求：消费方")
    @RequestMapping(value = "/client/getFile", method = RequestMethod.GET)
    public String getFile(String name) {
        logger.info("study-user服务，参数:{}", name);
        return fileServiceFeign.getFileHost(name);
    }

}
