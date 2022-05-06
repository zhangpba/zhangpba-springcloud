package com.study.city.entity.area;

import com.study.city.entity.area.Area;
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
