package com.study.city.data.service;

import com.study.city.data.entity.weather.JhWeather;

import java.util.List;

public interface IJhWeatherService {

    /**
     * 根据城市名称查询天气预报明细
     *
     * @param cityName 城市名称
     * @return
     */
    List<JhWeather> getWheatherByCity(String cityName);
}