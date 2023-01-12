package com.study.city.data.controller;

import com.study.city.data.service.IJhWeatherService;
import com.study.common.web.ResponseMessage;
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

import java.util.List;

/**
 * @author zhangpba
 * @description 聚合数据的天气预报
 * @date 2022/9/29
 */
@Api(value = "聚合数据-天气预报", tags = "聚合数据-天气预报")
@RestController
@EnableScheduling
@RequestMapping("/jh-weather")
public class JhWeatherController {
    private static final Logger logger = LoggerFactory.getLogger(JhWeatherController.class);

    @Autowired
    private IJhWeatherService jhWeatherService;

    @ApiOperation(value = "获取某一个地方的原始天气预报")
    @GetMapping(value = "/getWeather")
    public ResponseMessage getWeather(@ApiParam(name = "cityName", value = "城市名称", required = true) @RequestParam String cityName) {
        logger.info("入参：{}", cityName);
        List list = jhWeatherService.getWheatherByCity(cityName);
        return ResponseMessage.success(list);
    }
}
