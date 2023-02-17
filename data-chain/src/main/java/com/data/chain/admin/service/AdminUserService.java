package com.data.chain.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.admin.entity.AdminUser;
import com.data.chain.admin.vo.AdminUserVO;

/**
 * (AdminUser)表服务接口
 *
 * @author makejava
 * @since 2022-12-15 16:33:46
 */
public interface AdminUserService extends IService<AdminUser> {
    AdminUserVO login(String username, String password);

    void resetPassword(String username);

    void updatePassword(String username, String oldPassword, String newPassword);

}

