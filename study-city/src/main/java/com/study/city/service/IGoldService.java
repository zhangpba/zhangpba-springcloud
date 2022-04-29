package com.study.city.service;

import com.study.city.entity.gold.Gold;
import com.study.city.entity.gold.GoldBase;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
    List<Gold> getTodayGolds(String exchangeType);

    /**
     * 获取并保存所有黄金数据
     *
     * @return
     */
    String saveTodayGold();

    /**
     * 获取历史黄金数据
     *
     * @return
     */
    Map<String, List<Gold>> getHistoryGolds();

    /**
     * 批量增加黄金数据
     *
     * @param golds
     */
    void batchAddGolds(List<Gold> golds);

    /**
     * 根据日期和黄金类型查询
     *
     * @param type
     * @param date
     * @return
     */
    List<Gold> getGoldByTypeAndDate(String type, String date);


    /**
     * 按条件查询历史黄金数据
     *
     * @param exchangeType 交易所类型
     * @param startDate    开始时间
     * @param endDate      结束时间
     * @param type         黄金类型
     * @param typename     类型名称
     * @return 历史黄金数据
     */
    List<Gold> getGoldHistory(String exchangeType, String startDate, String endDate, String type, String typename);

    /**
     * 转化为各类黄金模型集合
     *
     * @param golds        查询出来的集合数据
     * @param exchangeType 交易所类型
     * @return
     */
    List<GoldBase> toGoldList(List<Gold> golds, String exchangeType);

    /**
     * 转化为各类黄金模型
     *
     * @param gold         查询出来的数据
     * @param exchangeType 交易所类型
     * @return
     */
    GoldBase toGoldInstance(Gold gold, String exchangeType);

    /**
     * execle导出黄金信息
     *
     * @param response     返回请求
     * @param exchangeType 城市名称
     * @param startDate    开始时间
     * @param endDate      结束时间
     */
    void export(HttpServletResponse response, String exchangeType, String startDate, String endDate);
}
