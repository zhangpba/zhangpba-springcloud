package com.study.city.mapper;

import com.study.city.entity.GroupBy;
import com.study.city.entity.weather.Weather;

import java.util.List;
import java.util.Map;

public interface WeatherMapper {

    void addWeather(Weather weather);

    void updateWeather(Weather weather);

    List<Weather> getWeather(Map map);

    List<Weather> getWeatherByCityAndDate(Map map);

    List<GroupBy> getGroupByType(Map map);
}
