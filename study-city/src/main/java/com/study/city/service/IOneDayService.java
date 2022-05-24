package com.study.city.service;

import com.study.city.entity.tianxing.OneDay;

public interface IOneDayService {
    // 获取每日一小句
    OneDay getOneDay();

    // 发送每日一小句邮件
    void sendOneDayEmail();
}