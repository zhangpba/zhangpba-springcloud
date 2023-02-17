package com.data.chain.report.dao;

import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpba
 * @description 报表数据访问层
 * @date 2023/1/4
 */
@Mapper
public interface ReportMapper {

    // 总体数据
    Map<String, Object> weekTotal(Map<String, Object> map);

    // 本周推送消息数量
    Map<String, Object> weekMessages(Map<String, Object> map);

    // 昨日推送平均用时
    Map<String, Object> yesterdayPushTime(Map<String, Object> map);

    // 当日推送消息分类统计
    List<Map<String, Object>> totdayPushType(Map<String, Object> map);

    // 当日企业接收信息数量TOP10
    List<Map<String, Object>> todayReciveNum(Map<String, Object> map);

    // 当日企业处置平均用时TOP10
    List<Map<String, Object>> todayHandleTime(Map<String, Object> map);

    // 本周企业超时处置TOP10
    List<Map<String, Object>> weekOverTime(Map<String, Object> map);

    // 查询平均处置时常TOP10
    List<Map<String, Object>>  queryAvgHandlerTime(Map<String, Object> map);
}
