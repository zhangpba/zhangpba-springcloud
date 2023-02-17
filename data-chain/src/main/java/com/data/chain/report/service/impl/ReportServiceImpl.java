package com.data.chain.report.service.impl;

import com.data.chain.report.dao.ReportMapper;
import com.data.chain.report.service.IReportService;
import com.data.chain.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpba
 * @description 报表服务
 * @date 2023/1/4
 */
@Service
public class ReportServiceImpl implements IReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Map<String, Object> weekTotal() {
        Map<String, Object> map = getWeekDay();
        Map<String, Object> result = reportMapper.weekTotal(map);
        if (result == null) {
            result = new HashMap<>();
            result.put("warnTotal", 0);
            result.put("noticeTotal", 0);
            result.put("companyTotal", 0);
            result.put("peopeltotal", 0);
        }
        return result;
    }

    @Override
    public Map<String, Object> weekMessages() {
        Map<String, Object> map = getWeekDay();
        return reportMapper.weekMessages(map);
    }

    @Override
    public Map<String, Object> yesterdayPushTime() {
        Map<String, Object> map = new HashMap<>();
        Date date = DateUtils.getYesterday();
        Date startTime = DateUtils.getTodayStartTime(date);
        Date endTime = DateUtils.getTodayEndTime(date);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        Map<String, Object> result = reportMapper.yesterdayPushTime(map);
        if (result == null) {
            result = new HashMap<>();
        }
        if (result.get("WARN_HANDLE_NUM") == null) {
            result.put("WARN_HANDLE_NUM", 0);
        }
        if (result.get("WARN_ACCEPT_NUM") == null) {
            result.put("WARN_ACCEPT_NUM", 0);
        }
        if (result.get("WARN_LOOK_NUM") == null) {
            result.put("WARN_LOOK_NUM", 0);
        }
        if (result.get("NOTICE_HANDLE_NUM") == null) {
            result.put("NOTICE_HANDLE_NUM", 0);
        }
        if (result.get("NOTICE_ACCEPT_NUM") == null) {
            result.put("NOTICE_ACCEPT_NUM", 0);
        }
        if (result.get("NOTICE_LOOK_NUM") == null) {
            result.put("NOTICE_LOOK_NUM", 0);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> totdayPushType() {
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        Date startTime = DateUtils.getTodayStartTime(date);
        Date endTime = DateUtils.getTodayEndTime(date);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return reportMapper.totdayPushType(map);
    }

    @Override
    public List<Map<String, Object>> todayReciveNum() {
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        Date startTime = DateUtils.getTodayStartTime(date);
        Date endTime = DateUtils.getTodayEndTime(date);
//        map.put("startTime", "2022-12-21 00:00:00");
//        map.put("endTime", "2022-12-31 23:59:59");
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return reportMapper.todayReciveNum(map);
    }

    @Override
    public List<Map<String, Object>> todayHandleTime() {
        Map<String, Object> todayMap = getTodayDay();
        List<Map<String, Object>> list = reportMapper.queryAvgHandlerTime(todayMap);
        List<String> stringList = new ArrayList<>();

        if (list != null && !list.isEmpty()) {
            for (Map<String, Object> m : list) {
                stringList.add((String) m.get("RECEIVER_NAME"));
                m.get("HANDLE");
            }
            todayMap.put("receiverNameList", stringList);
            return reportMapper.todayHandleTime(todayMap);
        } else {
            List<Map<String, Object>> mapList = new ArrayList<>();
            return mapList;
        }
    }

    @Override
    public List<Map<String, Object>> weekOverTime() {
        Map<String, Object> map = getWeekDay();
        return reportMapper.weekOverTime(map);
    }

    private Map<String, Object> getWeekDay() {
        Map<String, Object> map = new HashMap<>();
        Date currentDate = new Date();
        // 比如今天是2023-01-04
        List<Date> days = DateUtils.dateToWeek(currentDate);
        int i = 1;
        for (Date date : days) {

            Date startTime = DateUtils.getTodayStartTime(date);
            Date endTime = DateUtils.getTodayEndTime(date);

            String start = DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.format(startTime);
            String end = DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.format(endTime);

            map.put("startTime" + i, start);
            map.put("endTime" + i, end);
            i++;
        }

        logger.info("本周第一天: {}", map.get("startTime1"));
        logger.info("当天时间: {}", DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.format(currentDate));

        map.put("startTime", map.get("startTime1"));
        map.put("endTime", DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.format(currentDate));
        return map;
    }

    private Map<String, Object> getTodayDay() {
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        Date startTime = DateUtils.getTodayStartTime(date);
        Date endTime = DateUtils.getTodayEndTime(date);

        String start = DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.format(startTime);
        String end = DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.format(endTime);

        map.put("startTime", start);
        map.put("endTime", end);
        return map;
    }

}
