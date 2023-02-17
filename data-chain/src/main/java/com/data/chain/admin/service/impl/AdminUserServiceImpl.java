package com.data.chain.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.admin.dao.AdminUserDao;
import com.data.chain.admin.entity.AdminUser;
import com.data.chain.admin.service.AdminUserService;
import com.data.chain.admin.util.ApiLoginInfo;
import com.data.chain.admin.util.ApiTokenUtil;
import com.data.chain.admin.vo.AdminUserVO;
import com.data.chain.base.ResponseEnum;
import com.data.chain.config.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

/**
 * (AdminUser)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 16:33:46
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUser> implements AdminUserService {

    @Autowired
    private ApiTokenUtil apiTokenUtil;

    private static final String DEFAULT_PASSWORD = "123456";

    @Override
    public AdminUserVO login(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Optional<AdminUser> adminUserOptional = this.lambdaQuery().eq(AdminUser::getUsername, username)
                .eq(AdminUser::getPassword, password).oneOpt();
        if (!adminUserOptional.isPresent()) {
            throw new CustomException(ResponseEnum.ERROR_401);
        }
        AdminUser adminUser = adminUserOptional.get();
        AdminUserVO adminUserVO = new AdminUserVO();
        BeanUtils.copyProperties(adminUser, adminUserVO);
        ApiLoginInfo apiLoginInfo = ApiLoginInfo.builder()
                .userId(adminUser.getId())
                .username(adminUser.getUsername())
                .name(adminUser.getName())
                .build();
        String token = apiTokenUtil.addUser(apiLoginInfo);
        adminUserVO.setToken(token);
        return adminUserVO;
    }

    @Override
    public void resetPassword(String username) {
        String password = DigestUtils.md5DigestAsHex(DEFAULT_PASSWORD.getBytes());
        Optional<AdminUser> adminUserOptional = this.lambdaQuery().eq(AdminUser::getUsername, username).oneOpt();
        if (!adminUserOptional.isPresent()) {
            throw new CustomException(ResponseEnum.ERROR_401.getCode(), "用户不存在");
        }
        AdminUser adminUser = adminUserOptional.get();
        adminUser.setPassword(password);
        this.updateById(adminUser);
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        List<AdminUser> list = this.lambdaQuery().eq(AdminUser::getUsername, username)
                .eq(AdminUser::getPassword, oldPassword).list();
        if (CollectionUtils.isEmpty(list)) {
            throw new CustomException(ResponseEnum.ERROR_401.getCode(), "密码错误");
        }
        if (list.size() > 1) {
            throw new CustomException(ResponseEnum.FAIL);
        }
        AdminUser adminUser = list.get(0);
        adminUser.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        this.updateById(adminUser);
    }
}

