package com.study.city.service;

import com.github.pagehelper.PageInfo;
import com.study.city.entity.Weather;
import com.study.city.entity.WeatherResult;

import java.util.List;

public interface IWeatherService {

    /**
     * 根据城市名称查询原有天气预报
     *
     * @param cityName
     * @return
     */
    WeatherResult getWheatherResult(String cityName);

    /**
     * 根据城市名称查询天气预报明细
     *
     * @param cityName
     * @return
     */
    List<Weather> getWheatherByCity(String cityName);

    /**
     * 批量增加
     *
     * @param weatherEveDays
     */
    void batchAddWeathers(List<Weather> weatherEveDays);

    /**
     * 获取并保存所有城市的天气预报
     *
     * @return
     */
    String saveAllCityWeathers();

    /**
     * 分页查询
     * @param pageNUm
     * @param pageSize
     * @return
     */
    PageInfo getWeatherByPage(int pageNUm, int pageSize);

    /**
     * 根据日期和城市名查询
     *
     * @param cityName
     * @param date
     * @return
     */
    List<Weather> getWeatherByCityAndDate(String cityName, String date);
}
