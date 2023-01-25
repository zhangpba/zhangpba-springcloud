package com.study.city.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.data.constant.FeeApiUrl;
import com.study.city.data.entity.response.PyqResponse;
import com.study.city.data.entity.response.TianxingBaseResponse;
import com.study.city.data.service.IEmailService;
import com.study.city.data.service.ITxPyqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
//        emailService.sendEmail(map, pyqUsers);
        emailService.sendEmail(getTitle(map), getContent(map), "", pyqUsers);
    }

    @Override
    public void sendGdmjEmail() {
        Map<String, String> map = getGdmj();
//        emailService.sendEmail(map, gdmjUsers);
        emailService.sendEmail(getTitle(map), getContent(map), "", gdmjUsers);
    }

    // 发送土味情话
    @Override
    public void sendSayLoveEmail() {
        Map<String, String> map = getSayLove();
//        emailService.sendEmail(map, sayLoveUsers);
        emailService.sendEmail(getTitle(map), getContent(map), "", sayLoveUsers);
    }

    /**
     * 获取邮件标题
     *
     * @param map 源数据
     * @return 邮件标题
     */
    private String getTitle(Map<String, String> map) {
        // 设置邮件主题
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String date = format.format(new Date());
        String title = "%s的%s";
        title = String.format(title, date, map.get("desc"));
        return title;
    }

    /**
     * 设置邮件的正文
     *
     * @param map 源数据
     * @return 邮件正文
     */
    private String getContent(Map<String, String> map) {
        // 设置邮件的正文
        StringBuffer contentBuffer = new StringBuffer(map.get("content"));
        String source = map.get("source");
        if (source != null && !source.isEmpty()) {
            int size = contentBuffer.toString().length();
            contentBuffer.append(System.getProperty("line.separator"));
            for (int i = 0; i < size * 3; i++) {
                contentBuffer.append(" ");
            }
            contentBuffer.append("———");
            contentBuffer.append(source);
        }
        return contentBuffer.toString();
    }

    @Override
    public List<PyqResponse> pyqWenan() {
        return this.gatSourceData(FeeApiUrl.TIANXING_PYQWENAN_URL);
    }

    // 获取古典名句
    @Override
    public List<PyqResponse> gdmj() {
        return this.gatSourceData(FeeApiUrl.TIANXING_GDMJ_URL);
    }

    // 获取朋友圈文案
    @Override
    public List<PyqResponse> sayLove() {
        return this.gatSourceData(FeeApiUrl.TIANXING_SAYLOVE_URL);
    }

    private List<PyqResponse> gatSourceData(String feeApiUrl) {
        String url = String.format(feeApiUrl, key);
        PyqResponse response = null;
        ResponseEntity<TianxingBaseResponse> baseResponse = restTemplate.getForEntity(url, TianxingBaseResponse.class);
        logger.info("获取天行数据请求 url：{}", url);
        List<PyqResponse> newslist = new ArrayList<>();
        if (baseResponse.getStatusCode() == HttpStatus.OK) {
            if ("200".equals(baseResponse.getBody().getCode())) {
                newslist = baseResponse.getBody().getNewslist();
            }
        }
        return newslist;
    }
}
