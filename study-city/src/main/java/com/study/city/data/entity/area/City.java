package com.study.city.data.entity.area;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 市或者辖
 */
@Getter
@Setter
public class City {
    private String city;
    private String code;

    private List<Area> areas;
}
