package com.study.city.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.config.exception.CustomException;
import com.study.city.user.entity.SysUser;
import com.study.city.user.entity.request.SysUserListRequest;
import com.study.city.user.mapper.SysUserMapper;
import com.study.city.user.service.ISysUserService;
import com.study.city.utils.RedisUtils;
import com.study.city.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    RedisUtils redisUtils;

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
    public Integer deleteById(Integer userId) {
        return this.sysUserDao.deleteById(userId);
    }

    /**
     * 登录
     *
     * @param sysUser 用户
     * @return token
     */
    @Override
    public String login(SysUser sysUser) {
        String token = null;
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();
        if (username == null) {
            throw new CustomException(401, "用户名不能为空，请重新登录");
        }
        if (password == null) {
            throw new CustomException(401, "用户密码不能为空，请重新登录");
        }
        // 判断数据库中是否有该用户
        SysUser user = sysUserDao.login(username, password);
        if (user == null) {
            throw new CustomException(401, "用户不存在，请重新登录");
        }
        // 先去缓存中看一下是否有token
        token = TokenUtils.getToken(username, password);
//        token = getString(username, password);
        return token;
    }

    // 加入redis组件，如果用到这个方法，需要加入redis组件
    // redis中是token加上用户名为key，例如 token:zhangpba=qwertyuiosdfghjkdfghj
    private String getTokenFromRedis(String username, String password) {
        String tokenKey = TokenUtils.TOKEN_NAME + ":" + username;
        String token;
        if (redisUtils.get(tokenKey) != null) {
            token = (String) redisUtils.get(tokenKey);
        } else {
            // 生成token
            token = TokenUtils.getToken(username, password);
            // 有效期为30分钟
            redisUtils.set(tokenKey, token, 30L, TimeUnit.MINUTES);
        }
        return token;
    }
}
