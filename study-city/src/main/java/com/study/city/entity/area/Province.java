package com.study.city.entity.area;

import com.study.city.entity.area.City;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Province {
    private String area;
    private String name;
    private String generation;
    private String code;
    private String centre;
    private String type;

    // 省下面的市或者辖区
    private List<City> citys;
}
