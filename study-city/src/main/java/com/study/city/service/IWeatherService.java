package com.study.city.service;

import com.github.pagehelper.PageInfo;
import com.study.city.entity.GroupBy;
import com.study.city.entity.weather.Weather;
import com.study.city.entity.weather.WeatherResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

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
     * @param cityName 城市名称
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
     * 天气预报所需要的城市名称集合
     *
     * @return
     */
    Set<String> getAllCityForWeather();

    /**
     * 分页查询
     *
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param cityName  城市名称
     * @return
     */
    PageInfo getWeatherByPage(int pageNum, int pageSize, String startDate, String endDate, String cityName);

    /**
     * 根据日期和城市名查询
     *
     * @param cityName 城市名称
     * @param date     日期
     * @return
     */
    List<Weather> getWeatherByCityAndDate(String cityName, String date);

    /**
     * 利用模版生成word文档
     *
     * @param cityName   城市名称
     * @param tempPath   模版位置
     * @param targetPath 生成文件路径
     * @return
     */
    String createWord(String cityName, String tempPath, String targetPath);

    /**
     * execle导出天气预报
     *
     * @param response  返回请求
     * @param cityName  城市名称
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    void export(HttpServletResponse response, String cityName, String startDate, String endDate);

    /**
     * 查询在某段时间内城市的天气类型分组数据
     *
     * @param cityName  城市名称
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    List<GroupBy> getGroupByType(String cityName, String startDate, String endDate);
}
