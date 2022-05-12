package com.study.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.service.IPyqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PyqServiceImpl implements IPyqService {
    private static final Logger logger = LoggerFactory.getLogger(PyqServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender sender;

    @Value("${module.pyq.key}")
    private String key;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.send.users}")
    private String users;

    @Override
    public Map<String, String> getPyqWenan() {
        String url = String.format(FeeApiUrl.TIANXING_PYQWENAN_URL, key);
        Map<String, String> map = new HashMap<>();
        // 如果没有同步过，那么就请求API
        logger.info("获取朋友圈文案请求 url：{}", url);
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        String body = (String) responseEntity.getBody();
        JSONObject bodyJson = (JSONObject) JSON.parse(body);
        logger.info("请求返回：{}", bodyJson);
        String msg = bodyJson.getString("msg");
        Integer code = bodyJson.getInteger("code");
        JSONArray newslist = bodyJson.getJSONArray("newslist");
        if ("success".equals(msg)) {
            for (Object obj : newslist) {
                JSONObject newsJson = (JSONObject) obj;
                // 来源出处
                String source = newsJson.getString("source");
                // 内容
                String content = newsJson.getString("content");
                map.put("source", source);
                map.put("content", content);
            }
        }
        return map;
    }

    /**
     * 发送普通邮件
     */
    @Override
    public void sendEmail() {
        Map<String, String> map = getPyqWenan();
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String date = format.format(new Date());
        String title = "%s的问候";
        title = String.format(title, date);
        message.setSubject(title);
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom(from);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        String[] userList = users.split(",");
        message.setTo(userList);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText(map.get("content"));
        // 发送邮件
        sender.send(message);
    }
}
