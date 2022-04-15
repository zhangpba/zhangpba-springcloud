package com.study.city.service;

import com.study.city.entity.City;

import java.util.List;

public interface ICityService {
    // 单个插入数据
    void addCity(City city);

    // 根据省份查询城市
    List<City> getCityByProvince(String province);

    // 删除数据
    void deleteCity(Integer province);

    // 批量插入
    void batchAddCity(List<City> cities);

    // 解析redis数据
    List<City> parse(String result);

    // 查询所有的城市 2019-12-05
    List<City> getAllCity();
}
