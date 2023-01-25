package com.study.city.data.service;

import com.study.city.data.entity.response.LunarResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

public interface ITxLunarService {
    // 从天行数据获取老黄历数据
    List<Map<String, Object>> getLunar(String day);

    // 发送带Thymeleaf模板邮件
    void sendThymeleafMail(String day, String toUsers) throws MessagingException;

    // 从天行数据获取老黄历数据 2023-01-24
    List<LunarResponse> getLunarResponse(String day);
}


