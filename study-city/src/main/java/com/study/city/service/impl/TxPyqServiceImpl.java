package com.study.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.service.IEmailService;
import com.study.city.service.ITxPyqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TxPyqServiceImpl implements ITxPyqService {
    private static final Logger logger = LoggerFactory.getLogger(TxPyqServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IEmailService emailService;

    @Value("${spring.mail.send.pyq.key}")
    private String key;

    // 朋友圈文案需要发送的人
    @Value("${spring.mail.send.pyq.users}")
    private String pyqUsers;

    // 古典名句需要发送的人
    @Value("${spring.mail.send.gdmj.users}")
    private String gdmjUsers;

    // 土味情话需要发送的人
    @Value("${spring.mail.send.say-love.users}")
    private String sayLoveUsers;

    /**
     * 朋友圈文案
     *
     * @return
     */
    @Override
    public Map<String, String> getPyqWenan() {
        Map<String, String> map = getStringStringMap(FeeApiUrl.TIANXING_PYQWENAN_URL);
        map.put("desc", "朋友圈文案");
        return map;
    }

    /**
     * 古典名句
     *
     * @return
     */
    @Override
    public Map<String, String> getGdmj() {
        Map<String, String> map = getStringStringMap(FeeApiUrl.TIANXING_GDMJ_URL);
        map.put("desc", "古典名句");
        return map;
    }

    /**
     * 土味情话
     *
     * @return
     */
    @Override
    public Map<String, String> getSayLove() {
        Map<String, String> map = getStringStringMap(FeeApiUrl.TIANXING_SAYLOVE_URL);
        map.put("desc", "土味情话");
        return map;
    }

    private Map<String, String> getStringStringMap(String feeApiUrl) {
        String url = String.format(feeApiUrl, key);
        Map<String, String> map = new HashMap<>();
        logger.info("获取天行数据请求 url：{}", url);
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        String body = (String) responseEntity.getBody();
        JSONObject bodyJson = (JSONObject) JSON.parse(body);
        logger.info("获取天行数据返回：{}", bodyJson);
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

    @Override
    public void sendPyqEmail() {
        Map<String, String> map = getPyqWenan();
        emailService.sendEmail(map, pyqUsers);
    }

    @Override
    public void sendGdmjEmail() {
        Map<String, String> map = getGdmj();
        emailService.sendEmail(map, gdmjUsers);
    }

    // 发送土味情话
    @Override
    public void sendSayLoveEmail() {
        Map<String, String> map = getSayLove();
        emailService.sendEmail(map, sayLoveUsers);
    }
}
