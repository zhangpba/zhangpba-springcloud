package com.study.city.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Weather extends BaseEntity {
    // 原始数据
    // 最高温度 例如：高温 19℃
    private String high;
    // 风向 例如：东风
    private String fx;
    // 最低温度 例如：低温 7℃",
    private String low;
    // 风力 例如：<![CDATA[2级]]>
    private String fl;
    // 天气类型 例如：阴
    private String type;

    // 警告
    private String warn;
    // 城市
    private String city;
    private String date;
}
