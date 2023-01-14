package com.study.city.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.user.entity.SysUser;
import com.study.city.user.entity.request.SysUserListRequest;
import com.study.city.user.mapper.SysUserMapper;
import com.study.city.user.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-12 12:29:02
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {
    @Resource
    private SysUserMapper sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer userId) {
        return this.sysUserDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param sysUserRequest 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo<SysUser> queryByPage(SysUserListRequest sysUserRequest) {

        PageHelper.startPage(sysUserRequest.getPageNum(), sysUserRequest.getPageSize());
        List<SysUser> sysUsers = this.sysUserDao.queryAll(sysUserRequest);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.sysUserDao.deleteById(userId) > 0;
    }
}
