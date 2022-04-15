package com.study.city.controller;

import com.study.city.entity.Area;
import com.study.city.entity.Weather;
import com.study.city.entity.WeatherResult;
import com.study.city.service.IAreaService;
import com.study.city.service.IWeatherService;
import com.study.city.service.RedisUtils;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 天气预报控制类
 *
 * @author zhangpba
 * @date 2022-04-13
 */
@Api(value = "天气预报")
@RestController
@EnableScheduling
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IWeatherService weatherService;

    @ApiOperation(value = "获取某一个地方的原始天气预报")
    @GetMapping(value = "/get-source")
    public ResponseMessage getSource(@RequestParam(name = "cityName") @ApiParam(name = "城市名称") String cityName) {
        logger.info("入参：{}", cityName);
        WeatherResult weatherResult = weatherService.getWheatherResult(cityName);
        return ResponseMessage.success(weatherResult);
    }

    @ApiOperation(value = "获取某一个城市的6天的天气预报详情")
    @GetMapping(value = "/get-weathers")
    public ResponseMessage getWeathers(@RequestParam(name = "cityName") @ApiParam(name = "城市名称") String cityName) {
        logger.info("入参：{}");
        List<Weather> weatherEveDays = weatherService.getWheatherByCity(cityName);
        return ResponseMessage.success(weatherEveDays);
    }

    @ApiOperation(value = "保存天气预报详情 到redis")
    @PutMapping(value = "/save-weathers")
    public ResponseMessage saveWeathers(@RequestParam(name = "cityName") @ApiParam(name = "城市名称") String cityName) {
        logger.info("入参：{}");
        List<Weather> weatherEveDays = weatherService.getWheatherByCity(cityName);
        if (weatherEveDays != null) {
            redisUtils.hmSet("weather", cityName, weatherEveDays.toString());
        }
        return ResponseMessage.success("保存成功");
    }

    @ApiOperation(value = "获取所有城市天气预报")
    @GetMapping(value = "/all-city-weathers")
    public ResponseMessage allCityWeathers() {
        logger.info("入参：{}");

        List<String> cityNameList = areaService.eveCityNames();
        List<String> areaNamesList = areaService.eveAreaNames();

        Set<String> names = new HashSet<>();
        cityNameList.forEach(cityName -> {
            names.add(cityName);
        });
        areaNamesList.forEach(area->{
            names.add(area);
        });
        for (String name : names) {
            String cityName = "";
            if (name.contains("市")) {
                cityName = name.replace("市", "");
            }
            List<Weather> weatherEveDays = weatherService.getWheatherByCity(cityName);
            weatherService.batchAddWeathers(weatherEveDays);
        }
        return ResponseMessage.success("所有城市天气预报！");
    }

    //    @Scheduled(cron = "*/5 * * * * *")  // 每隔5秒执行一次
    @Scheduled(cron = "${weather.syn-cron}")    // 每天21点01
    public void allCityWeather() {
        logger.info("定时任务执行！！！！！");
        allCityWeathers();
    }
}
