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
}
