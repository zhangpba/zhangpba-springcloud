package com.study.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.email.EmailLog;
import com.study.city.entity.tianxing.OneDay;
import com.study.city.service.IEmailLogService;
import com.study.city.service.IEmailService;
import com.study.city.service.ITxOneDayService;
import com.study.city.service.ITxPyqService;
import com.study.starter.utils.ArraysUtils;
import com.study.starter.utils.DateUtils;
import com.study.starter.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Map;

@Service
public class TxOnedayServiceImpl implements ITxOneDayService {
    private static final Logger logger = LoggerFactory.getLogger(TxOnedayServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private ITxPyqService pyqService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IEmailLogService emailLogService;

    // 邮件发送人
    @Value("${spring.mail.username}")
    private String from;

    @Value("${module.one-day.key}")
    private String key;

    // 每日一小句需要发送的人
    @Value("${spring.mail.send.one-day.users}")
    private String oneDayUsers;

    /**
     * 获取每日一小句
     *
     * @return
     */
    @Override
    public OneDay getOneDay() {
        // 获取每日一小句
        OneDay oneDay = this.getStringStringMap(FeeApiUrl.TIANXING_ONE_URL);
        // 将图片进行本地存储
        this.saveImage(oneDay);
        return oneDay;
    }

    private OneDay getStringStringMap(String feeApiUrl) {
        String today = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD);
        String url = String.format(feeApiUrl, key, today);
        logger.info("获取天行数据【每日一小句】请求 url：{}", url);
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        String body = (String) responseEntity.getBody();
        JSONObject bodyJson = (JSONObject) JSON.parse(body);
        logger.info("获取天行数据返回：{}", bodyJson);
        String msg = bodyJson.getString("msg");
        JSONArray newslist = bodyJson.getJSONArray("newslist");

        OneDay oneDay = new OneDay();
        if ("success".equals(msg)) {
            for (Object obj : newslist) {
                JSONObject newsJson = (JSONObject) obj;
                // 来源出处
                Integer oneid = newsJson.getInteger("oneid");
                oneDay.setOneid(oneid);
                // 句子
                String word = newsJson.getString("word");
                oneDay.setWord(word);
                // 句子来源
                String wordfrom = newsJson.getString("wordfrom");
                oneDay.setWordfrom(wordfrom);
                // 配图
                String imgurl = newsJson.getString("imgurl");
                oneDay.setImgurl(imgurl);
                // 配图作者
                String imgauthor = newsJson.getString("imgauthor");
                oneDay.setImgauthor(imgauthor);
                // 时间
                String date = newsJson.getString("date");
                oneDay.setDate(date);
            }
        }
        return oneDay;
    }

    // 将图片链接保存到本地
    private String saveImage(OneDay oneDay) {
        // 本工程的位置
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "image" + System.getProperty("file.separator");
        String name = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD);
        filePath = filePath + name + ".jpg";
        ImageUtils.saveImage(oneDay.getImgurl(), filePath);
        oneDay.setImgPath(filePath);
        return filePath;
    }

    @Override
    public void sendOneDayEmail() {
        try {
            OneDay oneDay = getOneDay();
            sendImgResMail(oneDay, oneDayUsers);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     *
     * @param oneDay   每日一句
     * @param bccUsers 隐秘接受人
     * @throws MessagingException
     */
    private void sendImgResMail(OneDay oneDay, String bccUsers) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String title = "每日一小句";
        helper.setSubject("每日一小句");
        helper.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        String[] toUser = emailService.getToUser(null);
        emailService.usersLogs(toUser, "邮件接收者: ");
        helper.setTo(toUser);
        // 设置隐秘抄送人，可以有多个
        String[] bccUser = emailService.getBccUser(bccUsers);
        emailService.usersLogs(bccUser, "邮件隐秘抄送人: ");
        helper.setBcc(bccUser);
        helper.setSentDate(new Date());
        // src='cid:p01' 占位符写法 ，第二个参数true表示这是一个html文本
        String content = getContentOne(oneDay);
        helper.setText(content, true);
        // 第一个参数指的是html中占位符的名字，第二个参数就是文件的位置
        logger.info("图片路径：{}", oneDay.getImgPath());
        helper.addInline("p01", new FileSystemResource(new File(oneDay.getImgPath())));
        sender.send(mimeMessage);


        // 增加日志
        EmailLog emailLog = new EmailLog();
        emailLog.setTitle(title);
        emailLog.setContext(content);
        emailLog.setReceiveBcc(ArraysUtils.toString(bccUser));
        emailLog.setReceive(ArraysUtils.toString(toUser));
        emailLog.setSendUsers(from);
        emailLog.setCount(1);
        emailLog.setSendTime(new Date());
        emailLog.setCreateDate(new Date());
        emailLogService.addEmailLog(emailLog);
    }

    // 邮件内容
    private String getContentOne(OneDay oneDay) {
        // 增加土味情话
        Map<String, String> sayLoveMap = pyqService.getSayLove();
        String sayLove = sayLoveMap.get("content");
        String content = "<p>嗨 亲，早上好啊！每日的一声问候，希望能为了为您的生活带来一丝乐趣，请您欣赏</p><p><strong>土味情话: </strong></p><p><strong><h1>"
                + sayLove
                + "</h1></strong></p><p>每日一小句：</p><p><strong><h1>"
                + oneDay.getWord()
                + "</h1></strong></p><img src='cid:p01' width='1200' height='800'/>";
        return content;
    }

    // 邮件内容:土味情话、每日一小句、古典名句
    private String getContentTwo(OneDay oneDay) {
        // 增加土味情话
        Map<String, String> sayLoveMap = pyqService.getSayLove();
        String sayLove = sayLoveMap.get("content");
        // 增加古典名句
        Map<String, String> gdmjMap = pyqService.getGdmj();
        String gdmj = gdmjMap.get("content");
        String content = "<p>嗨 亲，早上好啊！每日的一声问候，希望能为了为您的生活带来一丝乐趣，请您欣赏</p><p><strong>土味情话: </strong></p><p><strong><h1>"
                + sayLove
                + "</h1></strong></p><p>每日一小句：</p><p><strong><h1>"
                + oneDay.getWord()
                + "</h1></strong></p><p>古典名句：</p><p><strong><h1>"
                + gdmj
                + "</h1></strong></p><img src='cid:p01' width='1200' height='800'/>";
        return content;
    }

}