package com.study.city.user.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpba
 * @description 用户登录
 * @date 2023/1/26
 */
@Setter
@Getter
public class LoginResponse {
    private String username;
    private String token;
}
