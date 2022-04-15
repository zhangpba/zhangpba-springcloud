package com.study.city.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {
    // 编号
    private Long code;
    // 城市名称
    private String cityName;
    // 省份
    private String province;
    // 英文拼写
    private String spell;
}
