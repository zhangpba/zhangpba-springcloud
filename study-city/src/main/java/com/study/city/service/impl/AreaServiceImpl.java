package com.study.city.service.impl;

import com.study.city.entity.Area;
import com.study.city.mapper.AreaMapper;
import com.study.city.service.IAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public void addArea(Area area) {
        areaMapper.addArea(area);
    }

    /**
     * 批量插入
     */
    @Override
    public void batchAddArea(List<Area> areas) {
        if (areas != null && areas.size() > 0) {
            logger.info("一共{}条数据需要插入表中", areas.size());
            areas.forEach(city -> {
                Area area = areaMapper.getAreaByCode(city.getCode());
                if (area == null) {
                    areaMapper.addArea(city);
                }
            });
        }
    }

    // 查询所有的城市 2019-12-05
    @Override
    public List<Area> getAllArea() {
        List<Area> areas = areaMapper.getAllArea();
        return areas;
    }

    @Override
    public Area getAreaByCode(String code) {
        return areaMapper.getAreaByCode(code);
    }

}
