package com.data.chain.admin.controller;

import com.data.chain.admin.service.AdminUserService;
import com.data.chain.admin.vo.AdminUserVO;
import com.data.chain.annotation.AdminAuth;
import com.data.chain.annotation.AdminAuthIgnore;
import com.data.chain.base.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/15
 */
@RestController
@RequestMapping("admin")
@Api(tags = "登陆管理")
@CrossOrigin
public class LoginController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("login")
    @ApiOperation(value = "登录", response = AdminUserVO.class)
    public ResponseVO login(@ApiParam("用户名") @RequestParam String username,
                            @ApiParam("密码") @RequestParam String password) {
        AdminUserVO adminUser = adminUserService.login(username, password);
        return ResponseVO.success(adminUser);
    }

    @AdminAuth
    @PostMapping("reset")
    @ApiOperation("重置密码")
    public ResponseVO reset(@ApiParam("用户名") @RequestParam String username) {
        adminUserService.resetPassword(username);
        return ResponseVO.success();
    }

    @AdminAuth
    @GetMapping("update")
    @ApiOperation("修改密码")
    public ResponseVO update(@ApiParam("用户名") @RequestParam  String username,
                             @ApiParam("旧密码") @RequestParam  String oldPassword,
                             @ApiParam("新密码") @RequestParam  String newPassword) {
        adminUserService.updatePassword(username, oldPassword, newPassword);
        return ResponseVO.success();
    }
}
