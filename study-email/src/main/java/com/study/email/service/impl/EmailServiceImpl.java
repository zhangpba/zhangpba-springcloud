package com.study.email.service.impl;

import com.study.email.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * 邮件测试类
 */
@Service
public class EmailServiceImpl implements IEmailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送纯文本的简单邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendSimple(String to, String subject, String content) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封普通文本测试邮件");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        message.setTo("2539971333@qq.com", "973098918@qq.com");
        // 设置邮件抄送人，可以有多个抄送人
//        message.setCc("12****32*qq.com");
        // 设置隐秘抄送人，可以有多个
//        message.setBcc("7******9@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("你好！我是发件人zhangpba!这是测试邮件的正文");
        // 发送邮件
        sender.send(message);
    }

    /**
     * 发送带附件的邮件
     */
    @Override
    public void sendAttachFileMail() throws MessagingException {
        MimeMessage mimeMailMessage = sender.createMimeMessage();
        // true表示构建一个可以带附件的邮件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
        helper.setSubject("这是一封带附件的邮件");
        helper.setFrom(from);
        helper.setTo("2539971333@qq.com");
//        helper.setCc("12****32*qq.com");
//        helper.setBcc("7******9@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是带附件的测试邮件正文！");
        // 第一个参数是自定义的名称，后缀需要加上，第二个参数是文件的位置
        helper.addAttachment("test.doc", new File("D:\\test.doc"));
        sender.send(mimeMailMessage);
    }

    /**
     * 发送带正文带图片的邮件
     */
    @Override
    public void sendImgResMail() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试图片的邮件");
        helper.setFrom(from);
        helper.setTo("2539971333@qq.com");
        //helper.setCc("37xxxxx37@qq.com");
        //helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        // src='cid:p01' 占位符写法 ，第二个参数true表示这是一个html文本
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>", true);
        // 第一个参数指的是html中占位符的名字，第二个参数就是文件的位置
        helper.addInline("p01", new FileSystemResource(new File("E:\\study\\图片1.jpg")));
        helper.addInline("p02", new FileSystemResource(new File("E:\\study\\图片2.jpg")));
        sender.send(mimeMessage);
    }


    /**
     * 测试Thymeleaf模板邮件
     */
    @Override
    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试Thymeleaf模板邮件");
        helper.setFrom(from);
        helper.setTo("2539971333@qq.com");
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        // 这里引入的是Template的Context
        Context context = new Context();
        // 设置模板中的变量
        context.setVariable("username", "javaboy-中国心");
        context.setVariable("num", "000001");
        context.setVariable("salary", "99999");
        // 第一个参数为模板的名称
        String process = templateEngine.process("hello.html", context);
        // 第二个参数true表示这是一个html文本
        helper.setText(process, true);
        sender.send(mimeMessage);
    }

}
