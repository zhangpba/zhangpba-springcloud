package com.study.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.weather.JhWeather;
import com.study.city.service.IJhWeatherService;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangpba
 * @description 聚合数据的天气预报
 * @date 2022/10/08
 */
@Api(value = "天行数据-网络取名", tags = "天行数据-网络取名")
@RestController
@EnableScheduling
@RequestMapping("/quname")
public class TxQnameController {
    private static final Logger logger = LoggerFactory.getLogger(TxQnameController.class);

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "获取某一个地方的原始天气预报")
    @GetMapping(value = "/create-name")
    public ResponseMessage createName(@ApiParam(name = "sex", value = "适合的性别，1男性、2女性、3中性[默认]", required = false) @RequestParam(required = false) String sex,
                                      @ApiParam(name = "wordnum", value = "名字的字数，默认2，最大3", required = false) @RequestParam(required = false) String wordnum,
                                      @ApiParam(name = "word", value = "名字中包含的字", required = false) @RequestParam(required = false) String word) {
        String url = String.format(FeeApiUrl.TIANXING_QNAME_URL, FeeApiUrl.TIANXING_QNAME_APP_KEY_YANGRQB);
        if (sex != null) {
            url = url + "&sex=" + sex;
        }
        if (wordnum != null) {
            url = url + "&wordnum=" + wordnum;
        }
        if (word != null) {
            url = url + "&word=" + word;
        }
        logger.info("请求url:{}", url);
        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        logger.info(jsonObject.toJSONString());
        int code = jsonObject.getInteger("code");
        if (code == 0) {
            logger.info("调用接口成功");
        }
        return ResponseMessage.success(jsonObject);
    }
}
