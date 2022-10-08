package com.study.user.service.impl;

import com.study.starter.vo.user.User;
import com.study.user.mapper.UserMapper;
import com.study.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpba
 * @description 用户逻辑层实现
 * @date 2022/10/8
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        // TODO 校验

        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        // TODO 校验

        return userMapper.updateUser(user);
    }

    @Override
    public List<User> getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public List<User> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return userMapper.login(map);
    }
}
