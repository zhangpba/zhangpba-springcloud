package com.study.city.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.data.entity.response.QQResponse;
import com.study.city.data.service.IQQService;
import com.study.city.user.entity.LoginResponse;
import com.study.city.user.entity.SysUser;
import com.study.city.user.entity.SysUserRole;
import com.study.city.user.entity.model.LoginUserVo;
import com.study.city.user.entity.request.SysRoleListRequest;
import com.study.city.user.entity.request.SysUserCreateRequest;
import com.study.city.user.entity.request.SysUserListRequest;
import com.study.city.user.mapper.SysUserMapper;
import com.study.city.user.mapper.SysUserRoleMapper;
import com.study.city.user.service.ISysUserService;
import com.study.city.utils.RedisUtils;
import com.study.city.utils.TokenUtils;
import com.study.common.exception.CustomException;
import com.study.common.utils.IpUtils;
import com.study.common.utils.JsonUtils;
import com.study.common.utils.ObjectUtils;
import com.study.common.utils.ServletUtils;
import com.study.common.utils.StringUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private RedisUtils redisUtils;

    @Value("${user.token.expire}")
    private Long tokenExpire;

    @Autowired
    private IQQService qqService;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer userId) {
        return this.sysUserMapper.queryById(userId);
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
        List<SysUser> sysUsers = this.sysUserMapper.queryAll(sysUserRequest);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param sysUserCreateRequest 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public SysUser insert(SysUserCreateRequest sysUserCreateRequest) {
        // 插入用户信息
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserCreateRequest, sysUser);
        this.sysUserMapper.insert(sysUser);

        // 插入角色信息
        List<SysRoleListRequest> sysRoleList = sysUserCreateRequest.getRoleList();
        Integer userId = sysUser.getUserId();
        List<SysUserRole> sysUserRoleList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysRoleList)) {
            for (SysRoleListRequest sysRole : sysRoleList) {
                if (sysRole.getRoleId() != null) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(sysRole.getRoleId());
                    sysUserRoleList.add(sysUserRole);
                }
            }
        } else {
            // 默认为考生
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(2L);
            sysUserRoleList.add(sysUserRole);
        }
        sysUserRoleMapper.insertBatch(sysUserRoleList);
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
        this.sysUserMapper.update(sysUser);
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
        return this.sysUserMapper.deleteById(userId);
    }

    /**
     * 登录
     *
     * @param sysUser 用户
     * @return token
     */
    @Override
    public LoginResponse login(SysUser sysUser) {
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
        SysUser user = sysUserMapper.login(username, password);
        if (user == null) {
            throw new CustomException(401, "用户不存在，请重新登录");
        }
        // 先去缓存中看一下是否有token
//        token = TokenUtils.getToken(username, password);
        token = this.getTokenFromRedis(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserId(user.getUserId());
        loginResponse.setUsername(sysUser.getUsername());
        loginResponse.setIsLine(0);

        // 获取用户头像
        String email = user.getEmail();
        if (!StringUtils.isEmpty(email) && email.contains("@qq.com")) {
            String qq = StringUtils.substring(email, 0, email.indexOf("@qq.com"));
            QQResponse qqResponse = qqService.getQQInfo(qq);
            if (ObjectUtils.isNotNull(qqResponse)) {
                loginResponse.setTouxiang(qqResponse.getTouxiang());
            }
        }

        return loginResponse;
    }

    /**
     * 获取登录用户的模型
     *
     * @param user 用户信息
     * @return
     */
    private String getTokenFromRedis(SysUser user) {
        LoginUserVo loginUser = null;
        try {
            loginUser = getLoginUser(user);
        } catch (IOException e) {
            throw new CustomException(-1, "缓存中没有用户！");
        }
        return loginUser.getToken();
    }

    // 加入redis组件，如果用到这个方法，需要加入redis组件
    // redis中是token加上用户名为key，例如 token:zhangpba=qwertyuiosdfghjkdfghj
    private LoginUserVo getLoginUser(SysUser user) throws IOException {
        String tokenKey = TokenUtils.TOKEN_NAME + ":" + user.getUsername();
        logger.info("key:{}", tokenKey);
        LoginUserVo loginUser = new LoginUserVo();
        if (redisUtils.get(tokenKey) != null) {
            String loginUserStr = (String) redisUtils.get(tokenKey);
            loginUser = JsonUtils.json2Object(loginUserStr, LoginUserVo.class);
        } else {
            // 生成token
            String token = TokenUtils.getToken(user.getUsername(), user.getPassword(), tokenExpire);
            // 把登录信息保存起来
            final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
            String ip = IpUtils.getRequestIp(ServletUtils.getRequest());
            // 获取客户端操作系统
            String os = userAgent.getOperatingSystem().getName();
            // 获取客户端浏览器
            String browser = userAgent.getBrowser().getName();
            // 过期时间
            loginUser.setExpireTime(new Date().getTime());
            loginUser.setUserId(user.getUserId());
            loginUser.setUsername(user.getUsername());
            loginUser.setBrowser(browser);
            loginUser.setIpaddr(ip);
            loginUser.setOs(os);
            loginUser.setToken(token);
            String userStr = JsonUtils.obj2Json(loginUser);
            // 有效期为30分钟
            redisUtils.set(tokenKey, userStr, 30L, TimeUnit.MINUTES);
        }
        return loginUser;
    }

    /**
     * 退出登录
     *
     * @param sysUser 用户
     * @return token
     */
    public String logOut(SysUser sysUser) {
        String tokenKey = TokenUtils.TOKEN_NAME + ":" + sysUser.getUsername();
        try {
            if (redisUtils.get(tokenKey) != null) {
                redisUtils.remove(tokenKey);
            }
            return sysUser.getUsername() + "退出登录!";
        } catch (Exception e) {
//            throw new CustomException(401, "退出失败！");
        }
        return sysUser.getUsername() + "退出登录!";
    }

    /**
     * 用户是否在线
     *
     * @param username 用户名
     * @return
     */
    public LoginResponse isLine(String username) {
        LoginResponse loginResponse = new LoginResponse();
        String tokenKey = TokenUtils.TOKEN_NAME + ":" + username;
        logger.info("key:{}", tokenKey);
        LoginUserVo loginUser = new LoginUserVo();
        // 在线返回token
        if (redisUtils.get(tokenKey) != null) {
            String loginUserStr = (String) redisUtils.get(tokenKey);
            loginUser = JsonUtils.json2Object(loginUserStr, LoginUserVo.class);
            loginResponse.setUsername(loginUser.getUsername());
            loginResponse.setToken(loginUser.getToken());
            loginResponse.setIsLine(0);
        } else {
            loginResponse.setUsername(username);
            loginResponse.setToken("");
            loginResponse.setIsLine(1);
        }
        return loginResponse;
    }
}
