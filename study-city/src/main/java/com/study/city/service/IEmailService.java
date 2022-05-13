package com.study.city.service;

import java.util.Map;

/**
 * 邮件帮助类
 */
public interface IEmailService {

    // 发送普通邮件
    void sendEmail(Map<String, String> resultMap, String toUsers);

    // 获取整理接收邮件的人
    String[] getToUser(String toUsersStr);
}


