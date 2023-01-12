package com.study.city.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.data.constant.FeeApiUrl;
import com.study.city.data.entity.email.EmailLog;
import com.study.city.data.service.IEmailLogService;
import com.study.city.data.service.IEmailService;
import com.study.city.data.service.ITxLunarService;
import com.study.common.utils.ArraysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TxLunarServiceImpl implements ITxLunarService {

    private static final Logger logger = LoggerFactory.getLogger(TxLunarServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IEmailLogService emailLogService;

    @Value("${spring.mail.send.lunar.key}")
    private String key;

    @Value("${spring.mail.username}")
    private String from;

    public List<Map<String, Object>> getLunar(String day) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String url = String.format(FeeApiUrl.TIANXING_LUNAR_URL, key, day);
        logger.info("查询{}的老黄历的请求 url：{}", day, url);
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        String body = (String) responseEntity.getBody();
        JSONObject bodyJson = (JSONObject) JSON.parse(body);
        logger.info("请求返回：{}", bodyJson);
        String msg = bodyJson.getString("msg");
        JSONArray newslist = bodyJson.getJSONArray("newslist");
        if ("success".equals(msg)) {
            for (Object obj : newslist) {
                Map<String, Object> map = new HashMap<>();
                JSONObject newsJson = (JSONObject) obj;
                map.put("gregoriandate", newsJson.getString("gregoriandate"));
                map.put("lunardate", newsJson.getString("lunardate"));
                map.put("lunar_festival", newsJson.getString("lunar_festival"));
                map.put("festival", newsJson.getString("festival"));
                map.put("fitness", newsJson.getString("fitness"));
                map.put("taboo", newsJson.getString("taboo"));
                map.put("shenwei", newsJson.getString("shenwei"));
                map.put("taishen", newsJson.getString("taishen"));
                map.put("chongsha", newsJson.getString("chongsha"));
                map.put("suisha", newsJson.getString("suisha"));
                map.put("wuxingjiazi", newsJson.getString("wuxingjiazi"));
                map.put("wuxingnayear", newsJson.getString("wuxingnayear"));
                map.put("wuxingnamonth", newsJson.getString("wuxingnamonth"));
                map.put("xingsu", newsJson.getString("xingsu"));
                map.put("pengzu", newsJson.getString("pengzu"));
                map.put("jianshen", newsJson.getString("jianshen"));
                map.put("tiangandizhiyear", newsJson.getString("tiangandizhiyear"));
                map.put("tiangandizhimonth", newsJson.getString("tiangandizhimonth"));
                map.put("tiangandizhiday", newsJson.getString("tiangandizhiday"));
                map.put("lmonthname", newsJson.getString("lmonthname"));
                map.put("shengxiao", newsJson.getString("shengxiao"));
                map.put("lubarmonth", newsJson.getString("lubarmonth"));
                map.put("lunarday", newsJson.getString("lunarday"));
                map.put("jieqi", newsJson.getString("jieqi"));
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * Thymeleaf模板邮件
     */
    @Override
    public void sendThymeleafMail(String day, String toUsers) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String title = "中国老黄历";
        helper.setSubject(title);
        helper.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        String[] userList = emailService.getToUser(toUsers);
        emailService.usersLogs(userList, "接收邮件的人:");
        helper.setTo(userList);
        helper.setSentDate(new Date());
        // 这里引入的是Template的Context
        Context context = new Context();
        // 设置模板中的变量
        List<Map<String, Object>> mapList = getLunar(day);
        Map<String, Object> map = mapList.get(0);
        context.setVariable("gregoriandate", map.get("gregoriandate"));
        context.setVariable("lunardate", map.get("lunardate"));
        context.setVariable("lunar_festival", map.get("lunar_festival"));
        context.setVariable("festival", map.get("festival"));
        context.setVariable("fitness", map.get("fitness"));
        context.setVariable("taboo", map.get("taboo"));
        context.setVariable("shenwei", map.get("shenwei"));
        context.setVariable("taishen", map.get("taishen"));
        context.setVariable("chongsha", map.get("chongsha"));
        context.setVariable("suisha", map.get("suisha"));
        context.setVariable("wuxingjiazi", map.get("wuxingjiazi"));
        context.setVariable("wuxingnayear", map.get("wuxingnayear"));
        context.setVariable("wuxingnamonth", map.get("wuxingnamonth"));
        context.setVariable("xingsu", map.get("xingsu"));
        context.setVariable("pengzu", map.get("pengzu"));
        context.setVariable("jianshen", map.get("jianshen"));
        context.setVariable("tiangandizhiyear", map.get("tiangandizhiyear"));
        context.setVariable("tiangandizhimonth", map.get("tiangandizhimonth"));
        context.setVariable("tiangandizhiday", map.get("tiangandizhiday"));
        context.setVariable("lmonthname", map.get("lmonthname"));
        context.setVariable("shengxiao", map.get("shengxiao"));
        context.setVariable("lubarmonth", map.get("lubarmonth"));
        context.setVariable("lunarday", map.get("lunarday"));
        context.setVariable("jieqi", map.get("jieqi"));
        logger.info("context: {}", context);
        // 第一个参数为模板的名称
        String process = templateEngine.process("老黄历.html", context);
        // 第二个参数true表示这是一个html文本
        helper.setText(process, true);
        sender.send(mimeMessage);


        // 增加日志
        EmailLog emailLog = new EmailLog();
        emailLog.setTitle(title);
        emailLog.setContext(context.toString());
        emailLog.setReceiveBcc(toUsers);
        emailLog.setReceive(ArraysUtils.toString(userList));
        emailLog.setSendUsers(from);
        emailLog.setCount(1);
        emailLog.setSendTime(new Date());
        emailLog.setCreateDate(new Date());
        emailLogService.addEmailLog(emailLog);
    }
}
