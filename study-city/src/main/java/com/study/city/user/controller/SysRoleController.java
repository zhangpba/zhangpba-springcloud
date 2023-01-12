package com.study.city.user.controller;

import com.study.city.user.entity.SysRole;
import com.study.city.user.entity.SysRoleRequest;
import com.study.city.user.service.SysRoleService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Dictionary;

/**
 * 角色信息表(SysRole)表控制层
 *
 * @author makejava
 * @since 2023-01-12 19:16:53
 */
@RestController
@RequestMapping("sysRole")
@Api(value = "用户角色", tags = "用户角色")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 分页查询角色
     *
     * @param sysRoleRequest 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @ApiOperation(value = "分页查询角色", response = Dictionary.class)
    public ResponseMessage<Page<SysRole>> queryByPage(SysRoleRequest sysRoleRequest) {
        return ResponseMessage.success(this.sysRoleService.queryByPage(sysRoleRequest));
    }

    /**
     * 通过主键查询单条角色
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条角色", response = Dictionary.class)
    public ResponseMessage<SysRole> queryById(@PathVariable("id") Long id) {
        return ResponseMessage.success(this.sysRoleService.queryById(id));
    }

    /**
     * 新增角色
     *
     * @param sysRole 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增角色", response = Dictionary.class)
    public ResponseMessage<SysRole> add(SysRole sysRole) {
        return ResponseMessage.success(this.sysRoleService.insert(sysRole));
    }

    /**
     * 编辑角色
     *
     * @param sysRole 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation(value = "编辑角色", response = Dictionary.class)
    public ResponseMessage<SysRole> edit(SysRole sysRole) {
        return ResponseMessage.success(this.sysRoleService.update(sysRole));
    }

    /**
     * 删除角色
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    @ApiOperation(value = "删除角色", response = Dictionary.class)
    public ResponseMessage<Boolean> deleteById(Long id) {
        return ResponseMessage.success(this.sysRoleService.deleteById(id));
    }

}

