package com.study.city.entity.weather;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpba
 * @description 聚合数据-天气预报
 * @date 2022/9/29
 */

@Setter
@Getter
public class JhWeather {

    String city;
    String info;
    String temperature;
    String humidity;
    String direct;
    String power;
    String aqi;
    String wid;
    String date;
}
