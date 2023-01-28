package com.study.city.user.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.annotation.LoginToken;
import com.study.city.user.entity.SysUser;
import com.study.city.user.entity.request.SysUserCreateRequest;
import com.study.city.user.entity.request.SysUserListRequest;
import com.study.city.user.service.ISysUserService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Dictionary;

/**
 * 用户管理
 *
 * @author zhangpba
 * @since 2023-01-12 12:28:49
 */
@RestController
@RequestMapping("sysUser")
@Api(value = "用户管理", tags = "用户管理")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    /**
     * 服务对象
     */
    @Resource
    private ISysUserService sysUserService;

    /**
     * 分页查询用户
     *
     * @param sysUserRequest 筛选条件分页对象
     * @return 查询结果
     */
    @PostMapping("/queryByPage")
    @ApiOperation(value = "分页查询", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<PageInfo<SysUser>> queryByPage(@RequestBody SysUserListRequest sysUserRequest) {
        logger.info("分页查询用户！");
        return ResponseMessage.success(this.sysUserService.queryByPage(sysUserRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条用户数据", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<SysUser> queryById(@PathVariable("id") Integer id) {
        logger.info("通过主键查询单条用户数据！");
        return ResponseMessage.success(this.sysUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUserCreateRequest 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增用户", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<SysUser> add(@RequestBody SysUserCreateRequest sysUserCreateRequest) {
        logger.info("新增用户！");
        return ResponseMessage.success(this.sysUserService.insert(sysUserCreateRequest));
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @PutMapping("/eidt")
    @ApiOperation(value = "编辑用户", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<SysUser> edit(@RequestBody SysUser sysUser) {
        logger.info("编辑用户！");
        return ResponseMessage.success(this.sysUserService.update(sysUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户数据", response = Dictionary.class)
    @LoginToken
    public ResponseMessage<Boolean> deleteById(@PathVariable("id") Integer id) {
        logger.info("删除用户！");
        return ResponseMessage.success(this.sysUserService.deleteById(id));
    }


    /**
     * 用户登录
     *
     * @param sysUser 实体
     * @return token
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录", response = Dictionary.class)
    public ResponseMessage<SysUser> login(@RequestBody SysUser sysUser) {
        logger.info("用户登录！");
        return ResponseMessage.success(this.sysUserService.login(sysUser));
    }

}

