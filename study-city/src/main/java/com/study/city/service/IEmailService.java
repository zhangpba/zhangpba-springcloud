package com.study.city.service;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * 邮件帮助类
 */
public interface IEmailService {

    // 发送普通邮件
    void sendEmail(Map<String, String> resultMap, String toUsers);

    // 发送带正文带图片的邮件
    void sendImgResMail(Map<String, Object> resultMap, String toUsers) throws MessagingException;

    // 获取整理接收邮件的人
    String[] getToUser(String toUsersStr);

    // 隐秘抄送人
    String[] getBccUser(String toUsersStr);

    // 打印邮件接受人、隐秘抄送人日志
    void usersLogs(String[] toUser, String title);
}


