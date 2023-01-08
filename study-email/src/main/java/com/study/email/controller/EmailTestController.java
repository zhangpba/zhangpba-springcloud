package com.study.email.controller;

import com.study.email.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;

/**
 * springboot如何发邮件
 *
 * @author zhangpba
 */
@RestController
public class EmailTestController {
    private static final Logger logger = LoggerFactory.getLogger(EmailTestController.class);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IEmailService emailService;

    /**
     * 发送普通邮件
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendEmail() {
        logger.info("study-email服务");
        emailService.sendSimple("2539971333@qq.com", "123", "你好啊！邮件测试");
        return "success!";
    }

    /**
     * 发送带附件的邮件
     */
    @RequestMapping(value = "/sendFile", method = RequestMethod.GET)
    public String sendAttachFileMail() {
        logger.info("study-email服务");
        try {
            emailService.sendAttachFileMail();
        } catch (MessagingException e) {
            return "fail !：" + e.getMessage();
        }
        return "success !";
    }

    /**
     * 发送带附件的邮件
     */
    @RequestMapping(value = "/sendImage", method = RequestMethod.GET)
    public String sendImgResMail() {
        logger.info("study-email服务");
        try {
            emailService.sendImgResMail();
        } catch (MessagingException e) {
            return "fail !：" + e.getMessage();
        }
        return "success !";
    }

    /**
     * 发送带附件的邮件
     */
    @RequestMapping(value = "/sendThymeleaf", method = RequestMethod.GET)
    public String sendThymeleafMail() {
        logger.info("study-email服务");
        try {
            emailService.sendThymeleafMail();
        } catch (MessagingException e) {
            return "fail !：" + e.getMessage();
        }
        return "success !";
    }
}
