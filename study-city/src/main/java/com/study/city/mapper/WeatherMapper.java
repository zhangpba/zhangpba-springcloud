package com.study.city.mapper;

import com.github.pagehelper.PageInfo;
import com.study.city.entity.Weather;

import java.util.List;
import java.util.Map;

public interface WeatherMapper {

    void addWeather(Weather weather);

    void updateWeather(Weather weather);

    List<Weather> getWeather(Map map);

    List<Weather> getWeatherByCityAndDate(Map map);
}
