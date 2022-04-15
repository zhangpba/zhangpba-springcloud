package com.study.city.service;

import com.study.city.entity.Area;

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
}


