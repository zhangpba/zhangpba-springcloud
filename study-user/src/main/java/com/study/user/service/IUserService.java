package com.study.user.service;

import com.study.common.entity.User;

import java.util.List;

/**
 * @author zhangpba
 * @description 用户逻辑层接口
 * @date 2022/10/8
 */
public interface IUserService {

    int addUser(User user);

    int updateUser(User user);

    List<User> getUser(User user);

    List<User> login(String username, String password);
}
