package com.study.city.data.mapper;

import com.study.city.data.entity.gold.Gold;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 黄金数据访问层
 *
 * @author zhangpab
 * @date 2022-04-16
 */
@Mapper
public interface GoldMapper {

    void addGold(Gold gold);

    void updateGlod(Gold gold);

    List<Gold> getGolds();

    List<Gold> getGoldByTypeAndDate(Map map);

    List<Gold> getGoldHistory(Map map);
}
