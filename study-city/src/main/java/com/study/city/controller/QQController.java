package com.study.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.utils.RestTemplateUtils;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpba
 * @description QQ信息
 * @date 2022/7/9
 */
@Api(value = "QQ信息", tags = "QQ信息")
@RestController
@RequestMapping("/qq")
public class QQController {
    private static final Logger logger = LoggerFactory.getLogger(QQController.class);

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    /**
     * 查询当日黄金
     *
     * @param QQ QQ号码
     * @return
     */
    @ApiOperation(value = "查询QQ信息")
    @GetMapping(value = "/getQQInfo")
    public ResponseMessage getQQInfo(@ApiParam(name = "QQ", value = "QQ号码", required = true) @RequestParam String QQ) {
        String url = "https://v.api.aa1.cn/api/qqjson/index.php?qq=" + QQ;

        ResponseEntity<String> response = restTemplateUtils.get(url, String.class);
        String body = response.getBody();
        String[] strings = body.split("JSON数据：");
        String result = strings[1];

        logger.info(result);
        JSONArray jsonArray = (JSONArray) JSON.parse(result);
        for (Object object : jsonArray) {
            JSONObject QQObject = (JSONObject) object;
            logger.info(QQObject.toJSONString());
            String touxiang = QQObject.getString("touxiang");
            String nickname = QQObject.getString("nickname");
            String email = QQObject.getString("email");

            logger.info("touxiang:{}", touxiang);
            logger.info("nickname:{}", nickname);
            logger.info("email:{}", email);

        }
        return ResponseMessage.success(jsonArray);
    }

}
