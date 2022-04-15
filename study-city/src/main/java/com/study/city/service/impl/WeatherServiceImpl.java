package com.study.city.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.entity.Weather;
import com.study.city.entity.WeatherResult;
import com.study.city.mapper.WeatherMapper;
import com.study.city.service.IWeatherService;
import com.study.starter.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherServiceImpl implements IWeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherMapper weatherEveDayMapper;

    public WeatherResult getWheatherResult(String cityName) {
        logger.info("{}的天气预报 start...", cityName);

        String url = "http://wthrcdn.etouch.cn/weather_mini?city=" + cityName;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        logger.info("{}的天气预报 返回: {}", cityName, responseEntity);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (!HttpStatus.OK.equals(statusCode)) {
            logger.error("请求失败！");
        }
        String body = responseEntity.getBody();
        JSONObject jsonBody = JSONObject.parseObject(body);

        // 返回原样集合
        WeatherResult weatherResult = new WeatherResult();

        // 返回集合
        List<Weather> weatherList = new ArrayList<>();

        String status = jsonBody.getString("status");
        if ("1000".equals(status)) {
            // 天气概况
            JSONObject data = jsonBody.getJSONObject("data");
            String city = data.getString("city");
            String ganmao = data.getString("ganmao");
            Integer wendu = data.getInteger("wendu");
            weatherResult.setCity(city);
            weatherResult.setGanmao(ganmao);
            weatherResult.setWendu(wendu);

            JSONArray forecast = data.getJSONArray("forecast");
            JSONObject yesterday = data.getJSONObject("yesterday");

            // 昨天天气
            Weather yesterdayWeather = new Weather();
            yesterdayWeather.setFl(yesterday.getString("fl"));
            yesterdayWeather.setHigh(yesterday.getString("high"));
            yesterdayWeather.setFx(yesterday.getString("fx"));
            yesterdayWeather.setLow(yesterday.getString("low"));
            yesterdayWeather.setType(yesterday.getString("type"));

            yesterdayWeather.setDate(DateUtils.format(DateUtils.getYesterday()));
            yesterdayWeather.setCity(city);
            yesterdayWeather.setUpdateDate(new Date());
            yesterdayWeather.setCreateDate(new Date());
            yesterdayWeather.setCreateBy("syn-admin");
            yesterdayWeather.setUpdateBy("syn-admin");
            weatherResult.setYesterday(yesterdayWeather);

            // 天气明细
            // 获取未来5天的明细数据

            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < forecast.size(); i++) {
                calendar.add(Calendar.DATE, i);
                Date timestamp = calendar.getTime();
                logger.info("timestamp:{}", timestamp);
                calendar.add(Calendar.DATE, -i);
                JSONObject weatherJson = (JSONObject) forecast.get(i);

                String fengli = weatherJson.getString("fengli");
                String fengxiang = weatherJson.getString("fengxiang");
                String date = weatherJson.getString("date");
                String high = weatherJson.getString("high");
                String low = weatherJson.getString("low");
                String type = weatherJson.getString("type");

                Weather weather = new Weather();
                weather.setDate(DateUtils.format(timestamp));
                weather.setFl(fengli);
                weather.setFx(fengxiang);
                weather.setHigh(high);
                weather.setLow(low);
                weather.setType(type);
                weather.setUpdateDate(new Date());
                weather.setCreateDate(new Date());
                weather.setCreateBy("syn-admin");
                weather.setUpdateBy("syn-admin");
                weather.setCity(city);
                if (i == 0) {
                    weather.setWarn(ganmao);
                }
                weatherList.add(weather);
            }
            weatherResult.setForecast(weatherList);
        } else {
            return null;
        }
        return weatherResult;
    }

    @Override
    public List<Weather> getWheatherByCity(String cityName) {
        WeatherResult weatherResult = getWheatherResult(cityName);
        if (weatherResult != null) {
            Weather yesterday = weatherResult.getYesterday();
            List<Weather> forecast = weatherResult.getForecast();
            forecast.add(0, yesterday);
            return forecast;
        } else {
            return null;
        }
    }

    @Override
    public List<Weather> getWeatherByCityAndDate(String cityName, String date) {
        Map<String, String> map = new HashMap<>();
        map.put("city", cityName);
        map.put("date", date);
        return weatherEveDayMapper.getWeatherByCityAndDate(map);
    }

    /**
     * 批量增加
     *
     * @param weatherList
     */
    @Override
    public void batchAddWeathers(List<Weather> weatherList) {
        if (weatherList != null) {
            for (Weather weather : weatherList) {
                List<Weather> list = getWeatherByCityAndDate(weather.getCity(), weather.getDate());
                if (list == null || list.isEmpty()) {
                    // 新增
                    weatherEveDayMapper.addWeather(weather);
                } else {
                    // 修改
                    weatherEveDayMapper.updateWeather(weather);
                }
            }
        }
    }
}