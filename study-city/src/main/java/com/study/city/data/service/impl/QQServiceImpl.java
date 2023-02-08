package com.study.city.data.service.impl;

import com.study.city.data.entity.response.QQResponse;
import com.study.city.data.service.IQQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangpba
 * @description QQ信息服务层
 * @date 2023/2/7
 */
@Service
public class QQServiceImpl implements IQQService {
    private static final Logger logger = LoggerFactory.getLogger(QQServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public QQResponse getQQInfo(String QQ) {
        String url = "https://v.api.aa1.cn/api/qqjson/index.php?qq=" + QQ;
        ResponseEntity<QQResponse> response = null;
        try {
            response = restTemplate.getForEntity(url, QQResponse.class);
            HttpStatus httpStatus = response.getStatusCode();
            QQResponse qqResponse = null;
            if (httpStatus == HttpStatus.OK) {
                qqResponse = response.getBody();
            }
            return qqResponse;
        } catch (Exception e) {
            logger.info("查询QQ信息异常！");
            return null;
        }
    }

}
