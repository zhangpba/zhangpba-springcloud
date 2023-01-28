package com.study.city.user.controller;

import com.github.pagehelper.PageInfo;
import com.study.city.user.entity.SysRole;
import com.study.city.user.entity.request.SysRoleListRequest;
import com.study.city.user.service.ISysRoleService;
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
 * 角色信息表(SysRole)表控制层
 *
 * @author zhangpba
 * @since 2023-01-12 19:16:53
 */
@RestController
@RequestMapping("sysRole")
@Api(value = "用户角色", tags = "用户角色")
public class SysRoleController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    /**
     * 服务对象
     */
    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 分页查询角色
     *
     * @param sysRoleRequest 筛选条件
     * @return 查询结果
     */
    @PostMapping("/queryByPage")
    @ApiOperation(value = "分页查询角色", response = Dictionary.class)
    public ResponseMessage<PageInfo<SysRole>> queryByPage(@RequestBody SysRoleListRequest sysRoleRequest) {
        logger.info("进入到分页查询角色方法！");
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
        logger.info("通过主键查询单条角色！");
        return ResponseMessage.success(this.sysRoleService.queryById(id));
    }

    /**
     * 新增角色
     *
     * @param sysRole 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增角色", response = Dictionary.class)
    public ResponseMessage<SysRole> add(@RequestBody SysRole sysRole) {
        logger.info("新增角色！");
        return ResponseMessage.success(this.sysRoleService.insert(sysRole));
    }

    /**
     * 编辑角色
     *
     * @param sysRole 实体
     * @return 编辑结果
     */
    @PutMapping("/eidt")
    @ApiOperation(value = "编辑角色", response = Dictionary.class)
    public ResponseMessage<SysRole> edit(@RequestBody SysRole sysRole) {
        logger.info("编辑角色！");
        return ResponseMessage.success(this.sysRoleService.update(sysRole));
    }

    /**
     * 删除角色
     *
     * @param roleId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{roleId}")
    @ApiOperation(value = "删除角色", response = Dictionary.class)
    public ResponseMessage<Boolean> deleteById(@PathVariable("roleId") Long roleId) {
        logger.info("删除角色！");
        return ResponseMessage.success(this.sysRoleService.deleteById(roleId));
    }
}

