package com.study.city.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.Weather;
import com.study.city.entity.WeatherResult;
import com.study.city.mapper.WeatherMapper;
import com.study.city.service.IAreaService;
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

    @Autowired
    private IAreaService areaService;

    /**
     * 解析天气预报
     *
     * @param cityName
     * @return
     */
    public WeatherResult getWheatherResult(String cityName) {
        logger.info("{}的天气预报 start...", cityName);

        String url = FeeApiUrl.WEATHER_URL + cityName;
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

            yesterdayWeather.setDate(DateUtils.format(DateUtils.getYesterday(), DateUtils.YYYY_MM_DD));
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
                calendar.add(Calendar.DATE, -i);
                JSONObject weatherJson = (JSONObject) forecast.get(i);

                String fengli = weatherJson.getString("fengli");
                String fengxiang = weatherJson.getString("fengxiang");
                String high = weatherJson.getString("high");
                String low = weatherJson.getString("low");
                String type = weatherJson.getString("type");

                Weather weather = new Weather();
                weather.setDate(DateUtils.format(timestamp, DateUtils.YYYY_MM_DD));
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
            // 请求失败
            logger.error("没有返回正常的天气预报信息！");
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
    public PageInfo getWeatherByPage(int pageNUm, int pageSize) {
        PageHelper.startPage(pageNUm,pageSize);
        List<Weather> weatherList = weatherEveDayMapper.getWeather();
        PageInfo<Weather> pageInfo = new PageInfo<>(weatherList);
        return pageInfo;
    }


    @Override
    public List<Weather> getWeatherByCityAndDate(String cityName, String date) {
        Map<String, String> map = new HashMap<>();
        map.put("city", cityName);
        map.put("date", date);
        List<Weather> weatherList = weatherEveDayMapper.getWeatherByCityAndDate(map);
        return weatherList;
    }

    /**
     * 获取所有城市的天气预报，并存入数据库中
     *
     * @return
     */
    @Override
    public String saveAllCityWeathers() {
        int success = 0;
        int fail = 0;

        StringBuffer successList = new StringBuffer();
        StringBuffer failList = new StringBuffer();

        List<String> cityNameList = areaService.eveCityNames();
        for (String name : cityNameList) {
            // 如果城市名称为空不进行请求
            if (name == null || name.isEmpty()) {
                continue;
            }
            String cityName = "";
            if (name.contains("市")) {
                cityName = name.replace("市", "");
            } else {
                cityName = name;
            }
            List<Weather> weatherList = this.getWheatherByCity(cityName);
            if (weatherList != null) {
                // 解析成功
                this.batchAddWeathers(weatherList);
                successList.append(cityName).append("、");
                success++;
            } else {
                // 解析失败
                fail++;
                failList.append(cityName).append("、");
            }
        }
        logger.info("成功城市名称：{}", successList);
        logger.info("失败城市名称：{}", failList);
        return "所有城市天气预报！成功：" + success
                + "条，失败：" + fail + "条,总共："
                + (success + fail) + "条！" + ">>>> 失败城市名称:" + failList;
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
