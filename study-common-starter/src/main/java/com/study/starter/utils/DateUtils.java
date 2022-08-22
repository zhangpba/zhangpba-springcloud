package com.study.starter.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 时间帮助类
 *
 * @author zhangpba
 * @date 2021-05-31
 */
public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String WEEKS = "weeks";
    public static final String DAYS = "days";

    private static SimpleDateFormat FORMAT_YYYY_MM_DD = new SimpleDateFormat(YYYY_MM_DD);
    private static SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

    /**
     * 格式化时间
     *
     * @param date 时间格式
     * @return 字符串
     */
    public static String format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 字符串转化时间
     *
     * @param date
     * @return
     */
    public static Date prase(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算两个时间之间的天数
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return 两天之间的天数
     */
    public static int betweenDays(String startDateStr, String endDateStr) {

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = FORMAT_YYYY_MM_DD.parse(startDateStr);
            endDate = FORMAT_YYYY_MM_DD.parse(endDateStr);
        } catch (Exception e) {
            // 日期型字符串格式错误
            System.out.println("日期型字符串格式错误");
        }
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
        return days - 1;
    }

    /**
     * 计算两个时间之间的天数
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 两天之间的天数
     */
    public static int betweenDays(Date startDate, Date endDate) {
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
        return days - 1;
    }

    /**
     * 计算两个时间之间的周数
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return 两天之间的周数
     * @author zhangpba
     * @date 2022-09-21
     */
    public static Map<String, Integer> betweenWeeks(String startDateStr, String endDateStr) {
        int days = betweenDays(startDateStr, endDateStr);
        return betweenWeeks(days);
    }

    /**
     * 天数转化为周数
     *
     * @param days 天数
     * @return 周数
     * @author zhangpba
     * @date 2022-09-21
     */
    public static Map<String, Integer> betweenWeeks(Integer days) {
        Map<String, Integer> map = new HashMap<>();
        if (days % 7 == 0) {
            map.put(WEEKS, days / 7);
        } else {
            map.put(WEEKS, days / 7);
            map.put(DAYS, days % 7);
        }
        return map;
    }


    /**
     * 获取某一时间之前的每一天
     *
     * @param date 从某一时间
     * @param days 天数
     * @return 从某一天开始之前的每一天
     */
    public static List<String> getbeforeDays(Date date, int days) {
        List<String> dayList = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        for (int i = 0; i < days; i++) {
            Date day = calendar.getTime();
            String dayStr = FORMAT_YYYY_MM_DD.format(day);
            dayList.add(dayStr);
            calendar.add(Calendar.DATE, -1);
        }
        return dayList;
    }


    /**
     * 获取某一时间之前的每一天
     *
     * @param date 从某一时间
     * @param days 天数
     * @return 从某一天开始之前的每一天
     */
    public static List<String> getAfterDays(Date date, int days) {
        List<String> dayList = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        for (int i = 0; i < days; i++) {
            Date day = calendar.getTime();
            String dayStr = FORMAT_YYYY_MM_DD.format(day);
            dayList.add(dayStr);
            calendar.add(Calendar.DATE, 1);
        }
        return dayList;
    }

    /**
     * 获取之前、之后的某一天
     *
     * @param date 从某一时间
     * @param days 天数：-/+ 之前/之后
     * @return 从某一天开始之前的某一天
     */
    public static String getDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        Date day = calendar.getTime();
        String dayStr = FORMAT_YYYY_MM_DD.format(day);
        return dayStr;
    }

    // 获取昨天
    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
