package com.study.city.data.service;

/**
 * 邮件提醒帮助类
 */
public interface IRemindService {

    // 发送提醒邮件，提醒下雨天点外卖
    void sendEmail();

    // 获取发送邮件内容
    String getContent();
}