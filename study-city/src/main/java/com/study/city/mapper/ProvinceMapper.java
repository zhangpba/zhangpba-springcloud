package com.study.city.mapper;

import com.study.city.entity.area.Province;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 获取区域
 */
@Mapper
public interface ProvinceMapper {
    // 查询所有的省
    List<Province> getAllProvincelist();

    // 根据省编码查询 省下面的市或者辖区
    Province getProvinceByCode(String provinceCode);

    // 根据省编码查询 省下面的市或者辖区
    Province getProvinceByName(String name);


}
