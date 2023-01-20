package com.study.city.user.service;


import com.github.pagehelper.PageInfo;
import com.study.city.user.entity.SysUser;
import com.study.city.user.entity.request.SysUserListRequest;

/**
 * (SysUser)表服务接口
 *
 * @author zhangpba
 * @since 2023-01-12 12:28:59
 */
public interface ISysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Integer userId);

    /**
     * 分页查询
     *
     * @param sysUserRequest 筛选条件
     * @return 查询结果
     */
    PageInfo<SysUser> queryByPage(SysUserListRequest sysUserRequest);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    Integer deleteById(Integer userId);

    /**
     * 登录
     *
     * @param sysUser 用户
     * @return token
     */
    String login(SysUser sysUser);
}
