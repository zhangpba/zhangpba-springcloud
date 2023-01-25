package com.study.city.data.entity.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhangpba
 * @description QQ响应体
 * @date 2023/1/25
 */
@Setter
@Getter
public class QQResponse implements Serializable {
    // 昵称
    private String nickname;
    // 头像
    private String touxiang;
    // qq邮箱
    private String email;
}