package com.study.city.controller.test;

import com.study.city.fegin.UserServiceFegin;
import com.study.starter.vo.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 天气预报控制类
 *
 * @author zhangpba
 * @date 2022-10-08
 */
@Api(value = "用户", tags = "测试调用用户服务")
@RestController
@EnableScheduling
@RequestMapping("/user")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserServiceFegin userServiceFegin;

    @ApiOperation(value = "根据条件查询用户")
    @GetMapping(value = "/getUser")
    public List<User> getUsers(@ApiParam(name = "username", value = "用户名称", required = true) @RequestParam String username) {
        logger.info("入参：{}", username);
        User user = new User();
        user.setUsername(username);
        return userServiceFegin.getUser(user);
    }
}
