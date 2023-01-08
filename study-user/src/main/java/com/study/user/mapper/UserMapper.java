package com.study.user.mapper;

import com.study.common.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int addUser(User user);

    int updateUser(User user);

    List<User> getUser(User user);

    List<User> login(Map map);
}