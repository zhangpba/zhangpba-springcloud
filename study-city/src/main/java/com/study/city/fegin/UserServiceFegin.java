package com.study.city.fegin;

import com.study.city.hystrix.UserServiceFallBack;
import com.study.starter.vo.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author zhangpba
 * @description 用户服务接口
 * @date 2022/10/8
 */
@FeignClient(value = "study-user",fallback = UserServiceFallBack.class)
public interface UserServiceFegin {

    // 查询用户
    @RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<User> getUser(@RequestBody User user);
}