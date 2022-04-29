package com.study.email.service;

import javax.mail.MessagingException;

public interface IEmailService {

    /**
     * 发送普通文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimple(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @throws MessagingException
     */
    void sendAttachFileMail() throws MessagingException;

    /**
     * 发送带正文带图片的邮件
     *
     * @throws MessagingException
     */
    public void sendImgResMail() throws MessagingException;

    /**
     * 测试Thymeleaf模板邮件
     *
     * @throws MessagingException
     */
    void sendThymeleafMail() throws MessagingException;
}
