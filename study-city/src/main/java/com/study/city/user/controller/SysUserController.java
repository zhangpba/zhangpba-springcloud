package com.study.city.user.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.user.entity.SysUser;
import com.study.city.user.entity.request.SysUserListRequest;
import com.study.city.user.service.ISysUserService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
     * 分页查询
     *
     * @param sysUserRequest 筛选条件分页对象
     * @return 查询结果
     */
    @PostMapping("/queryByPage")
    @ApiOperation(value = "分页查询", response = Dictionary.class)
    public ResponseMessage<PageInfo<SysUser>> queryByPage(@RequestBody SysUserListRequest sysUserRequest) {
        logger.info("分页查询！");
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
    public ResponseMessage<SysUser> queryById(@PathVariable("id") Integer id) {
        logger.info("通过主键查询单条用户数据！");
        return ResponseMessage.success(this.sysUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增用户数据", response = Dictionary.class)
    public ResponseMessage<SysUser> add(@RequestBody SysUser sysUser) {
        logger.info("新增用户数据！");
        return ResponseMessage.success(this.sysUserService.insert(sysUser));
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation(value = "编辑用户数据", response = Dictionary.class)
    public ResponseMessage<SysUser> edit(@RequestBody SysUser sysUser) {
        logger.info("编辑用户数据！");
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
    public ResponseMessage<Boolean> deleteById(@PathVariable("id") Integer id) {
        logger.info("删除用户数据！");
        return ResponseMessage.success(this.sysUserService.deleteById(id));
    }

}

