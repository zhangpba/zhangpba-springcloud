package com.study.city.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.weather.JhWeather;
import com.study.city.service.IJhWeatherService;
import com.study.starter.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JhWeatherServiceImpl implements IJhWeatherService {
    private static final Logger logger = LoggerFactory.getLogger(JhWeatherServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据城市名称查询天气预报明细
     *
     * @param cityName 城市名称
     * @return
     */
    @Override
    public List<JhWeather> getWheatherByCity(String cityName) {
        String url = String.format(FeeApiUrl.JUHE_WEATHER_URL, cityName, FeeApiUrl.JUHE_WEATHER_API_KEY);
        String response = restTemplate.getForObject(url, String.class);

        List<JhWeather> list = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(response);
        int error_code = jsonObject.getInteger("error_code");
        if (error_code == 0) {
            logger.info("调用接口成功");

            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject realtime = result.getJSONObject("realtime");

            String city = result.getString("city");
            String info = realtime.getString("info");
            String temperature = realtime.getString("temperature");
            String humidity = realtime.getString("humidity");
            String direct = realtime.getString("direct");
            String power = realtime.getString("power");
            String aqi = realtime.getString("aqi");
            String wid = realtime.getString("wid");

            JhWeather weather = new JhWeather();
            weather.setCity(city); // 城市
            weather.setInfo(info); // 天气
            weather.setHumidity(humidity); // 湿度
            weather.setDirect(direct); // 风向
            weather.setPower(power); // 风力
            weather.setTemperature(temperature); // 温度
            weather.setWid(wid);
            weather.setAqi(aqi); // 空气质量
            String date = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            weather.setDate(date);

            list.add(weather);

            JSONArray future = result.getJSONArray("future");
            for (Object o : future) {
                JSONObject weatherJson = (JSONObject) o;

                String dateFuture = weatherJson.getString("date");
                String infoFuture = weatherJson.getString("weather");
                String temperatureFuture = weatherJson.getString("temperature");
                String directFuture = weatherJson.getString("direct");
                String widFuture = weatherJson.getString("wid");

                JhWeather weatherFuture = new JhWeather();
                weatherFuture.setCity(city); // 城市
                weatherFuture.setInfo(infoFuture); // 天气
                weatherFuture.setDirect(directFuture); // 风向
                weatherFuture.setTemperature(temperatureFuture); // 温度
                weatherFuture.setWid(widFuture);
                weatherFuture.setDate(dateFuture);

                list.add(weatherFuture);
            }
            return list;
        } else {
            logger.error("调用接口失败：" + jsonObject.getString("reason"));
            return null;
        }
    }

}
