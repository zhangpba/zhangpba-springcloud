package com.study.common.utils;

import java.text.ParseException;
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

    // 1987-01-01
    public static final String YYYY_MM = "yyyy-MM";
    // 1987-01-01
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    // 1987-01-01 23:59:59
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    // 1987-01-01 星期一
    public static final String YYYY_MM_DD_EEE = "yyyy-MM-dd EEE";

    public static final String YEARS = "years";
    public static final String MONTHS = "months";
    public static final String WEEKS = "weeks";
    public static final String DAYS = "days";

    private static SimpleDateFormat FORMAT_YYYY_MM = new SimpleDateFormat(YYYY_MM);
    private static SimpleDateFormat FORMAT_YYYY_MM_DD = new SimpleDateFormat(YYYY_MM_DD);
    private static SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    private static SimpleDateFormat FORMAT_YYYY_MM_DD_EEE = new SimpleDateFormat(YYYY_MM_DD_EEE);

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

    /**
     * 根据日期获得所在自然周的日期
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

    /**
     * 获取某一天的零点时间戳（00:00:00）
     *
     * @param date 某一天的时间
     * @return 某一天的00:00:00
     */
    public static long getTodayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取某一天的12点时间戳（23:59:59）
     *
     * @param date 某一天的时间
     * @return 某一天的23:59:59
     */
    public static long getTodayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime();
    }

    /**
     * 获取本年度的十二个月份 yyyy-MM
     *
     * @return 本年度的十二个月份
     */
    public static List<String> getMonthByYear() {
        List<String> data = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            // 获取当前的年份
            int year = calendar.get(Calendar.YEAR);
            // 开始日期为当前年拼接1月份
            Date startDate = FORMAT_YYYY_MM.parse(year + "-01");
            // 结束日期为当前年拼接12月份
            Date endDate = FORMAT_YYYY_MM.parse(year + "-12");
            // 设置calendar的开始日期
            calendar.setTime(startDate);
            // 当前时间小于等于设定的结束时间
            while (calendar.getTime().compareTo(endDate) <= 0) {
                String time = FORMAT_YYYY_MM.format(calendar.getTime());
                data.add(time);
                // 当前月份加1
                calendar.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取本月度的每一天 yyyy-MM-dd
     *
     * @return 本月的每一天集合
     */
    public static List<String> getDayByMonth() {
        List<String> data = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            // 获取当前的年份
            int year = calendar.get(Calendar.YEAR);
            // 获取当前的月份（需要加1才是现在的月份）
            int month = calendar.get(Calendar.MONTH) + 1;
            // 获取本月的总天数
            int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 开始日期为当前年月拼接1号
            Date startDate = FORMAT_YYYY_MM_DD.parse(year + "-" + month + "-01");
            // 结束日期为当前年月拼接该月最大天数
            Date endDate = FORMAT_YYYY_MM_DD.parse(year + "-" + month + "-" + dayCount);
            // 设置calendar的开始日期
            calendar.setTime(startDate);
            // 当前时间小于等于设定的结束时间
            while (calendar.getTime().compareTo(endDate) <= 0) {
                String time = FORMAT_YYYY_MM_DD.format(calendar.getTime());
                data.add(time);
                // 当前日期加1
                calendar.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取本周的所有日期 yyyy-MM-dd
     *
     * @return
     */
    public static List<String> getDayByWeek() {
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
            Date dBegin = FORMAT_YYYY_MM_DD.parse(startTime);
            Date dEnd = FORMAT_YYYY_MM_DD.parse(endTime);
            //获取这周所有date
            List<Date> lDate = findDates(dBegin, dEnd);
            for (Date date : lDate) {
                data.add(FORMAT_YYYY_MM_DD.format(date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 根据指定日期获取一周的第一天和最后一天日期
     *
     * @param date
     * @return
     */
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
        String imptimeBegin = FORMAT_YYYY_MM_DD.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = FORMAT_YYYY_MM_DD.format(cal.getTime());
        return imptimeBegin + "," + imptimeEnd;
    }


    /**
     * 查找两日期之间的日期
     *
     * @param dBegin 开始日期
     * @param dEnd   结束日期
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
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }
}
