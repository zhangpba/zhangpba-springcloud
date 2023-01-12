package com.study.city.data.service;

import com.study.city.data.entity.area.Area;
import com.study.city.data.entity.area.City;

import java.util.List;

public interface IAreaService {
    // 单个插入数据
    void addArea(Area area);

    // 批量插入
    void batchAddArea(List<Area> areas);

    // 查询所有的城市 2019-12-05
    List<Area> getAllArea();

    Area getAreaByCode(String code);

    // 去重后的所有城市名
    List<String> eveCityNames();

    // 去重后的所有区县名
    List<String> eveAreaNames();

    // 根据省编码查询 省下面的市或者辖区
    List<City> getCityListByCode(String provinceCode);

    // 查询 省下面的市、辖区 所关联的区域
    List<Area> getAreaListByCode(String cityCode);

    // 根据省编码查询 省下面的市或者辖区、以及辖区下面的地域
    List<City> getAreaByProvince(String provinceCode);

    // 初始化区域数据
    String initArea();
}


