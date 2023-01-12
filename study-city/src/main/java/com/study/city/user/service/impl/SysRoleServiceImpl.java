package com.study.city.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.user.entity.SysRole;
import com.study.city.user.entity.request.SysRoleListRequest;
import com.study.city.user.mapper.SysRoleMapper;
import com.study.city.user.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2023-01-12 19:17:06
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Long roleId) {
        return this.sysRoleDao.queryById(roleId);
    }

    /**
     * 分页查询
     *
     * @param sysRoleRequest 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo<SysRole> queryByPage(SysRoleListRequest sysRoleRequest) {
        PageHelper.startPage(sysRoleRequest.getPageNum(), sysRoleRequest.getPageSize());
        List<SysRole> sysUsers = this.sysRoleDao.queryAllByLimit(sysRoleRequest);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysUsers);

        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole insert(SysRole sysRole) {
        this.sysRoleDao.insert(sysRole);
        return sysRole;
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole update(SysRole sysRole) {
        this.sysRoleDao.update(sysRole);
        return this.queryById(sysRole.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.sysRoleDao.deleteById(roleId) > 0;
    }
}
