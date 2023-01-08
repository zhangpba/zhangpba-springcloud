package com.study.city.service.impl;

import com.study.city.entity.email.EmailLog;
import com.study.city.service.IEmailLogService;
import com.study.city.service.IEmailService;
import com.study.common.utils.ArraysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
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

    // 默认隐秘抄送人
    @Value("${spring.mail.send.default.bcc}")
    private String bccUsers;

    @Autowired
    private IEmailLogService emailLogService;

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
        this.usersLogs(userList, "邮箱接收人:");
        message.setTo(userList);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        StringBuffer contentBuffer = new StringBuffer(resultMap.get("content"));
        String source = resultMap.get("source");
        if (source != null && !source.isEmpty()) {
            int size = contentBuffer.toString().length();
            contentBuffer.append(System.getProperty("line.separator"));
            for (int i = 0; i < size * 3; i++) {
                contentBuffer.append(" ");
            }

            contentBuffer.append("———");
            contentBuffer.append(source);
        }
        message.setText(contentBuffer.toString());
        // 发送邮件
        sender.send(message);

        // 增加日志
        EmailLog emailLog = new EmailLog();
        emailLog.setTitle(title);
        emailLog.setContext(contentBuffer.toString());
        emailLog.setReceiveBcc(null);
        emailLog.setReceive(ArraysUtils.toString(userList));
        emailLog.setSendUsers(from);
        emailLog.setCount(1);
        emailLog.setSendTime(new Date());
        emailLog.setCreateDate(new Date());
        emailLogService.addEmailLog(emailLog);
    }


    /**
     * 发送普通邮件
     *
     * @param title     标题
     * @param content   邮件内容
     * @param toUsers   接收人
     * @param toBccUser 隐秘接收人
     */
    @Override
    public void sendEmail(String title, String content, String toUsers, String toBccUser) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        String[] userList = getToUser(toUsers);
        this.usersLogs(userList, "邮箱接收人:");
        message.setTo(userList);
        // 隐秘抄送人
        String[] bccUser = getBccUser(toBccUser);
        this.usersLogs(bccUser, "隐秘接收人:");
        message.setBcc(bccUser);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText(content);
        // 发送邮件
        sender.send(message);
        // 增加日志
        EmailLog emailLog = new EmailLog();
        emailLog.setTitle(title);
        emailLog.setContext(content);
        emailLog.setReceiveBcc(null);
        emailLog.setReceive(ArraysUtils.toString(userList));
        emailLog.setSendUsers(from);
        emailLog.setCount(1);
        emailLog.setSendTime(new Date());
        emailLog.setCreateDate(new Date());
        emailLogService.addEmailLog(emailLog);
    }

    /**
     * 发送带正文带图片的邮件 demo
     */
    @Override
    public void sendImgResMail(Map<String, Object> resultMap, String toUsers) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试图片的邮件");
        helper.setFrom(from);
        helper.setTo(toUsers);
        helper.setSentDate(new Date());
        // src='cid:p01' 占位符写法 ，第二个参数true表示这是一个html文本
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>", true);
        // 第一个参数指的是html中占位符的名字，第二个参数就是文件的位置
        helper.addInline("p01", new FileSystemResource(new File("E:\\study\\图片1.jpg")));
        helper.addInline("p02", new FileSystemResource(new File("E:\\study\\图片2.jpg")));
        sender.send(mimeMessage);
    }


    /**
     * 获取发送的人
     *
     * @param toUsersStr 传入的收件人
     * @return
     */
    @Override
    public String[] getToUser(String toUsersStr) {
        return getBase(toUsersStr, users);
    }

    /**
     * 隐秘抄送人
     *
     * @param toUsersStr 隐秘抄送人
     * @return
     */
    @Override
    public String[] getBccUser(String toUsersStr) {
        return getBase(toUsersStr, bccUsers);
    }

    // 如果是接口传进来的，都视为收件人；配置文件中的均为隐秘抄送人
    public String[] getBase(String toUsersStr, String users) {
        List<String> userlist = new ArrayList<>();
        // 传入的收件人
        if (toUsersStr != null && toUsersStr.contains(",")) {
            String[] toUsers = toUsersStr.split(",");
            for (String u : toUsers) {
                userlist.add(u);
            }
        } else {
            if (toUsersStr != null && !toUsersStr.isEmpty()) {
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


    /**
     * 打印邮件接受人、隐秘抄送人日志
     *
     * @param toUser 邮箱数组
     * @param title  描述
     */
    @Override
    public void usersLogs(String[] toUser, String title) {
        if (toUser != null && toUser.length != 0) {
            StringBuffer toUserBuffer = new StringBuffer(title);
            for (int i = 0; i < toUser.length; i++) {
                toUserBuffer.append(toUser[i]);
                if (i != toUser.length - 1) {
                    toUserBuffer.append(",");
                }
            }
            logger.info(toUserBuffer.toString());
        }
    }
}
