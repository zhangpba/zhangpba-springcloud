package com.study.city.mapper;

import com.study.city.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {
    void addCity(City city);

    // 根据省份查询市
    List<City> getCityByProvince(String province);

    void deleteCity(Integer province);

    City getCityByCode(Long code);

    // 查询所有的城市 2019-12-05
    List<City> getAllCity();
}
