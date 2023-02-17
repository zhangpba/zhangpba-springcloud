package com.data.chain.utils;

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
    public static final SimpleDateFormat YYYY_MM_DD_HH_EEE = new SimpleDateFormat("yyyy-MM-dd EEE");

    public static final String YEARS = "years";
    public static final String MONTHS = "months";
    public static final String WEEKS = "weeks";
    public static final String DAYS = "days";

    public static SimpleDateFormat FORMAT_YYYY_MM_DD = new SimpleDateFormat(YYYY_MM_DD);
    public static SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

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
        return (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 计算两个时间之间的天数
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 两天之间的天数
     */
    public static int betweenDays(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
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
     * 根据生日计算出年龄
     *
     * @param birthDay 生日
     * @return 年龄：age:{months=8, days=13, yesrs=18} 代表18岁零8个月13天
     * @throws Exception
     */
    public static Map<String, Object> getAge(String birthDay) throws Exception {
        Map<String, Object> result = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        Date birthDayAge = sdf.parse(birthDay);
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDayAge)) { // 出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException("日期填写错误！！");
        }
        // 当前年份
        int yearNow = cal.get(Calendar.YEAR);
        // 当前月份
        int monthNow = cal.get(Calendar.MONTH);
        // 当前日期
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDayAge);
        // 生日年份
        int yearBirth = cal.get(Calendar.YEAR);
        // 生日月份
        int monthBirth = cal.get(Calendar.MONTH);
        // 生日日期
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        // 计算整岁数
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    // 当前日期在生日之前，年龄减一
                    age--;
                }
            } else {
                // 当前月份在生日之前，年龄减一
                age--;
            }
        }
        result.put(YEARS, age);

        // 零几个月
        int month = 0;
        // 当前月份小于等于生日月份
        if (monthNow < monthBirth) {
            month = monthBirth - monthNow;
            month--;
        } else if (monthNow >= monthBirth) {
            month = monthNow - monthBirth;
            // 当前日期小于生日日期
            if (dayOfMonthNow <= dayOfMonthBirth) {
                month--;
            }
        }
        result.put(MONTHS, month);

        // 零几天
        int day = 0;
        if (dayOfMonthNow > dayOfMonthBirth) {
            day = dayOfMonthNow - dayOfMonthBirth;
        } else {
            day = dayOfMonthNow;
        }
        result.put(DAYS, day);
        return result;
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
     * 根据日期获得所在周的日期
     *
     * @param mdate
     * @return 所在周的日期
     */
    @SuppressWarnings("deprecation")
    public static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a - 1, fdate);
        }
        return list;
    }

    // 获取某一天的零点 （00:00:00）
    public static Date getTodayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    // 获取某一天的12点的时间 （23:59:59）
    public static Date getTodayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
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


    public static void main(String[] args) {
        List<String> list = getDayByWeek();
        for (String s:list) {
            System.out.println(s);
        }
    }

    public static List<String> getDayByWeek(){
        List<String> data = new ArrayList<>();
        try {
            //获取本周时间
            String yzTime = getTimeInterval(new Date());
            String[] time = yzTime.split(",");
            //本周第一天
            String startTime = time[0];
            //本周最后一天
            String endTime = time[1];
            //格式化日期
            Date dBegin = YYYY_MM_DD_HH_EEE.parse(startTime);
            Date dEnd = YYYY_MM_DD_HH_EEE.parse(endTime);
            //获取这周所有date
            List<Date> lDate = findDates(dBegin, dEnd);
            for (Date date : lDate) {
                data.add(YYYY_MM_DD_HH_EEE.format(date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // 根据指定日期获取一周的第一天和最后一天日期
    public static String getTimeInterval(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = YYYY_MM_DD_HH_EEE.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = YYYY_MM_DD_HH_EEE.format(cal.getTime());
        return imptimeBegin + "," + imptimeEnd;
    }


    /**
     * 查找日期
     * @param dBegin 开始日期
     * @param dEnd 结束日期
     * @return List<Date>
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<>();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())){
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

}
