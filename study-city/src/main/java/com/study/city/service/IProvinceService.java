package com.study.city.service;

import com.study.city.entity.Province;
import com.study.city.entity.Weather;

import java.util.List;

public interface IProvinceService {

    // 查询所有的省
    List<Province> getAllProvinceDesc();

    // 查询所有的省及其下属部门信息
    List<Province> getAllProvince();

    // 根据省编码获取名称查询 省下面的市或者辖区
    Province getProvinceByCodeOrName(String provinceCodeOrName);
}
