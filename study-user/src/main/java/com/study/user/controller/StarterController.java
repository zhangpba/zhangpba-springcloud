package com.study.user.controller;

import com.study.starter.service.DatasourceService;
import com.study.starter.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试starter
 *
 * @author zhangpba
 * @date 2022-04-11
 */
@Api(value = "测试starter")
@RestController
public class StarterController {
    private static final Logger logger = LoggerFactory.getLogger(StarterController.class);

    @Autowired
    private DemoService demoService;

    @Autowired
    private DatasourceService datasourceService;

    /**
     * 测试get请求：消费方
     *
     * @return
     */
    @ApiOperation(value = "测试starter")
    @RequestMapping(value = "/starter", method = RequestMethod.GET)
    public String starter() {
        logger.info("study-user服务，starter方法");
        demoService.say();
        return datasourceService.toString();
    }

}
