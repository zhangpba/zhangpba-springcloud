package com.study.city.service;

import java.util.Map;

public interface IPyqService {
    Map<String, String> getPyqWenan();

    // 发送邮件
    void sendEmail();
}


