package com.study.city.service;

import com.study.city.entity.Gold;

import java.util.List;

/**
 * 黄金数据实现类接口
 *
 * @author zhangpab
 * @date 2022-04-16
 */
public interface IGoldService {

    /**
     * 获取黄金数据模型
     *
     * @return
     */
    List<Gold> getGoldList();

    /**
     * 批量增加黄金数据
     *
     * @param golds
     */
    void batchAddGolds(List<Gold> golds);

    /**
     * 获取并保存所有黄金数据
     *
     * @return
     */
    String saveAllGolds();

    /**
     * 根据日期和黄金类型查询
     *
     * @param type
     * @param date
     * @return
     */
    List<Gold> getGoldByTypeAndDate(String type, String date);
}
