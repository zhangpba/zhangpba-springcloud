package com.study.city.data.entity.weather;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class WeatherRequest {

    // 页码
    private Integer pageNum;

    // 页面大小
    private Integer pageSize;

    // 城市名称
    private String cityName;

    // 开始时间
    private Date startDate;

    // 结束时间
    private Date endDate;
}
