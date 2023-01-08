package com.study.user.controller;

import com.study.common.entity.User;
import com.study.common.web.ResponseMessage;
import com.study.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangpba
 * @description 用户控制层
 * @date 2022/9/30
 */
@Api(value = "user")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(StarterController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "查询用户")
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public List<User> getUser(@RequestBody User user) {
        logger.info("study-user，查询用户列表");
        List<User> users = userService.getUser(user);
//        throw new RuntimeException("发生了人为异常");
        return users;
    }

    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/addUser")
    public ResponseMessage addUser(@RequestBody User user) {
        logger.info("增加用户！{}", user);
        int num = userService.addUser(user);
        return ResponseMessage.success("增加成功" + num + "条");
    }

    @ApiOperation(value = "新增用户")
    @GetMapping(value = "/insert")
    public ResponseMessage insert(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
                                  @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password,
                                  @ApiParam(name = "email", value = "邮箱", required = true) @RequestParam String email,
                                  @ApiParam(name = "phone", value = "电话", required = true) @RequestParam String phone,
                                  @ApiParam(name = "address", value = "地址", required = true) @RequestParam String address,
                                  @ApiParam(name = "age", value = "年龄", required = true) @RequestParam Integer age,
                                  @ApiParam(name = "birthday", value = "生日", required = true) @RequestParam String birthday,
                                  @ApiParam(name = "sex", value = "新宝贝", required = true) @RequestParam Integer sex) {

        User user = new User();
        user.setAddress(address);
        user.setAge(age);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUsername(username);
        int num = userService.addUser(user);
        return ResponseMessage.success("增加成功" + num + "条");
    }
}
