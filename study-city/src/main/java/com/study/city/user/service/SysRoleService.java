package com.study.city.user.service;

import com.github.pagehelper.PageInfo;
import com.study.city.user.entity.SysRole;
import com.study.city.user.entity.request.SysRoleListRequest;

/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2023-01-12 19:17:02
 */
public interface SysRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysRole queryById(Long roleId);

    /**
     * 分页查询
     *
     * @param sysRoleRequest 筛选条件
     * @return 查询结果
     */
    PageInfo<SysRole> queryByPage(SysRoleListRequest sysRoleRequest);

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    SysRole insert(SysRole sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    SysRole update(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

}
