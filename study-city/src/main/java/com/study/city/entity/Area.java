package com.study.city.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Area {
    private String area;
    private String city;
    private String province;
    private String code;

    // 父级ID
    private String parCode;
}
