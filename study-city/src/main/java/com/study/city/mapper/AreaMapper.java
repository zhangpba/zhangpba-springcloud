package com.study.city.mapper;

import com.study.city.entity.Area;
import com.study.city.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 获取区域
 */
@Mapper
public interface AreaMapper {
    void addArea(Area area);

    // 根据省份查询市
//    List<City> getCityByProvince(String province);
//
//    void deleteCity(Integer province);
//
//    City getCityByCode(Long code);

    // 查询所有的城市 2019-12-05
    List<Area> getAllArea();

    Area getAreaByCode(String code);
}
