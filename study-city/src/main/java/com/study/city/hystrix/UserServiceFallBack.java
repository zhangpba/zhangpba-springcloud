package com.study.city.hystrix;

import com.study.city.fegin.UserServiceFegin;
import com.study.starter.vo.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 断路器-降级处理
 *
 * @date 2021-08-26
 */
@Component
public class UserServiceFallBack implements UserServiceFegin {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceFallBack.class);

    // 进行降级处理
    @Override
    public List<User> getUser(User user) {
        List<User> users = new ArrayList<>();
        User u = new User();
        u.setUsername("没有查到用户的信息");
        users.add(u);
        return users;
    }
}
