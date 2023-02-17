package com.data.chain.report.service;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpba
 * @description 报表服务
 * @date 2023/1/3
 */
public interface IReportService {

    // 总体数据
    Map<String, Object> weekTotal();

    // 本周推送消息数量")
    Map<String, Object> weekMessages();

    // 昨日推送平均用时
    Map<String, Object> yesterdayPushTime();

    // 当日推送消息分类统计
    List<Map<String, Object>> totdayPushType();

    // 当日企业接收信息数量TOP10
    List<Map<String, Object>> todayReciveNum();

    // 当日企业处置平均用时TOP10
    List<Map<String, Object>> todayHandleTime();

    // 本周企业超时处置TOP10
    List<Map<String, Object>> weekOverTime();

}
