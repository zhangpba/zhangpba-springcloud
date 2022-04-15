package com.study.city.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.entity.City;
import com.study.city.mapper.CityMapper;
import com.study.city.service.ICityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityServiceImpl implements ICityService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Override
    public void addCity(City city) {
        cityMapper.addCity(city);
    }

    @Override
    public List<City> getCityByProvince(String province) {
        return cityMapper.getCityByProvince(province);
    }

    @Override
    public void deleteCity(Integer province) {
        cityMapper.deleteCity(province);
    }

    /**
     * 批量插入
     */
    @Override
    public void batchAddCity(List<City> cities) {
        if (cities != null && cities.size() > 0) {
            logger.info("一共{}条数据需要插入表中", cities.size());
            cities.forEach(city -> {
                City c = cityMapper.getCityByCode(city.getCode());
                if (c == null) {
                    cityMapper.addCity(city);
                }
            });
        }
    }

    /**
     * 解析报文
     *
     * @param result
     */
    public List<City> parse(String result) {
        Map<Long, City> cityMap = new HashMap<>();
        List<City> cities = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray datas = jsonObject.getJSONArray("data");
        if (datas != null && datas.size() > 0) {
            logger.info("redis的数据一共{}条", datas.size());
            int count = 0;
            for (Object o : datas) {
                JSONObject data = (JSONObject) o;
                Long code = data.getLong("code");
                String province = data.getString("province");
                String spell = data.getString("spell");
                String title = data.getString("title");
                City city = new City();
                city.setCode(code);
                city.setCityName(title);
                city.setProvince(province);
                city.setSpell(spell);

                // 过滤
                if (cityMap.get(code) != null) {
                    count += 1;
                    continue;
                }
                cityMap.put(code, city);
                cities.add(city);
            }
            logger.info("过滤了{}条数据", count);
        }
        return cities;
    }

    // 查询所有的城市 2019-12-05
    @Override
    public List<City> getAllCity() {
        List<City> cities = cityMapper.getAllCity();
        return cities;
    }
}
