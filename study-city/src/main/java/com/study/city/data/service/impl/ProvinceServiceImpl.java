package com.study.city.data.service.impl;

import com.study.city.data.entity.area.City;
import com.study.city.data.entity.area.Province;
import com.study.city.data.mapper.ProvinceMapper;
import com.study.city.data.service.IAreaService;
import com.study.city.data.service.IProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements IProvinceService {
    private static final Logger logger = LoggerFactory.getLogger(ProvinceServiceImpl.class);

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private IAreaService areaService;

    // 查询所有的省信息
    @Override
    public List<Province> getAllProvinceDesc() {
        return provinceMapper.getAllProvincelist();
    }

    // 查询所有的省及其下属部门信息
    @Override
    public List<Province> getAllProvince() {
        // 全国所有的省
        List<Province> provinces = this.getAllProvinceDesc();
        provinces.forEach(province -> {
            // 一个市或者辖区的所有区
            List<City> cityList = areaService.getAreaByProvince(province.getCode());
            province.setCitys(cityList);
        });
        return provinces;
    }


    // 根据省编码或者省名称查询 省下面的市
    @Override
    public List<City> getCityDesc(String provinceCode) {
        List<City> cityList = areaService.getCityListByCode(provinceCode);
        return cityList;
    }

    // 根据省编码或者省名称查询 省下面的市或者辖区
    @Override
    public Province getProvinceByCodeOrName(String provinceCodeOrName) {
        Province province = provinceMapper.getProvinceByName(provinceCodeOrName);
        if (province != null) {
            return province;
        } else{
            return provinceMapper.getProvinceByCode(provinceCodeOrName);
        }
    }
}
