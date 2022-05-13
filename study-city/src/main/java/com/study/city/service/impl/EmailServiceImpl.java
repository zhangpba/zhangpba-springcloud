package com.study.city.service.impl;

import com.study.city.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements IEmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender sender;

    // 邮件发送人
    @Value("${spring.mail.username}")
    private String from;

    // 默认邮件接收人
    @Value("${spring.mail.send.default.users}")
    private String users;

    /**
     * 发送普通邮件
     */
    @Override
    public void sendEmail(Map<String, String> resultMap, String toUsers) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String date = format.format(new Date());
        String title = "%s的%s";
        title = String.format(title, date, resultMap.get("desc"));
        message.setSubject(title);
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        String[] userList = getToUser(toUsers);
        logger.info("邮箱接收人：{}", userList);
        message.setTo(userList);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText(resultMap.get("content"));
        // 发送邮件
        sender.send(message);
    }


    /**
     * 获取发送的人
     *
     * @param toUsersStr 传入的收件人
     * @return
     */
    @Override
    public String[] getToUser(String toUsersStr) {
        List<String> userlist = new ArrayList<>();
        // 传入的收件人
        if (toUsersStr != null && toUsersStr.contains(",")) {
            String[] toUsers = toUsersStr.split(",");
            for (String u : toUsers) {
                userlist.add(u);
            }
        } else {
            if (toUsersStr != null || !toUsersStr.isEmpty()) {
                userlist.add(toUsersStr);
            }
        }
        // 默认收件人
        String[] userDefault = users.split(",");
        for (String u : userDefault) {
            userlist.add(u);
        }
        return userlist.toArray(new String[userlist.size()]);
    }
}
