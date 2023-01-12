package com.study.city.data.service.impl;

import com.study.city.data.entity.Dict;
import com.study.city.data.entity.weather.JhWeather;
import com.study.city.data.service.IDictService;
import com.study.city.data.service.IEmailService;
import com.study.city.data.service.IJhWeatherService;
import com.study.city.data.service.IRemindService;
import com.study.city.data.service.ITxLunarService;
import com.study.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RemindServiceImpl implements IRemindService {
    private static final Logger logger = LoggerFactory.getLogger(RemindServiceImpl.class);

    @Autowired
    private IJhWeatherService jhWeatherService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private ITxLunarService txLunarService;

    @Autowired
    private IDictService dictService;

    // 朋友圈文案需要发送的人
    @Value("${spring.mail.send.remind.users}")
    private String remindBccUsers;


    // 朋友圈文案需要发送的人
    @Value("${spring.mail.send.remind.birthday}")
    private String remindBirthDay;

    @Override
    public void sendEmail() {
        String date = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD);
        // 实时查询今天天气
        List<JhWeather> weatherList = jhWeatherService.getWheatherByCity("西安");
        JhWeather jhWeather = null;
        if (weatherList != null && !weatherList.isEmpty()) {
            for (JhWeather w : weatherList) {
                if (w.getDate().equals(date)) {
                    jhWeather = w;
                }
            }
        }

        // 发送提醒邮件
        sendEmail(jhWeather.getInfo());
    }

    /**
     * 发送普通邮件
     */
    private void sendEmail(String weatherInfo) {
        // 邮件主题
        String title = "温馨提示";
        // 设置邮件的正文
        String content = getContent(weatherInfo);
        emailService.sendEmail(title, content, "", remindBccUsers);
    }

    // 获取邮件内容
    private String getContent(String weatherInfo) {
        // 比较生日
        Date date = DateUtils.prase(remindBirthDay, DateUtils.YYYY_MM_DD);
        // 要是当前日期在生日之前，是预产期
        if (new Date().getTime() < date.getTime()) {
            return getPreDeliveryContent(weatherInfo);
        } else {
            // 要是当前日期在生日之前，是生日
            return getBirthdayContent(weatherInfo);
        }
    }

    /**
     * 预产期提醒内容
     *
     * @param weatherInfo
     * @return
     */
    private String getPreDeliveryContent(String weatherInfo) {
        String today = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD);
        int alreadyDays = DateUtils.betweenDays("2022-03-15", today);
        int needDays = DateUtils.betweenDays(today, remindBirthDay);
        // 加上周提示
        String alreadyWeeks = getWeeks(alreadyDays);
        String needWeeks = getWeeks(needDays);

        StringBuffer contentBuffer = new StringBuffer("亲，宝宝已经在你肚子里待了");
        contentBuffer.append(alreadyDays);
        contentBuffer.append("天了(");
        contentBuffer.append(alreadyWeeks);
        contentBuffer.append("),");
        contentBuffer.append(System.getProperty("line.separator"));
        contentBuffer.append("还有");
        contentBuffer.append(needDays);
        contentBuffer.append("天(");
        contentBuffer.append(needWeeks);
        contentBuffer.append(")宝宝就要出来跟我们见面啦！");
        contentBuffer.append(System.getProperty("line.separator"));
        contentBuffer.append("要注意饮食、注意劳逸结合呦");
        contentBuffer.append(System.getProperty("line.separator"));
        contentBuffer.append(System.getProperty("line.separator"));

        List<Map<String, Object>> lunarMap = txLunarService.getLunar(today);
        if (lunarMap != null && !lunarMap.isEmpty()) {
            Map<String, Object> lunar = lunarMap.get(0);
            // 国际节日
            if (lunar.get("festival") != null) {
                String festival = (String) lunar.get("festival");
                if (!"".equals(festival)) {
                    contentBuffer.append("今天是公历节日：");
                    contentBuffer.append(festival);
                    contentBuffer.append(System.getProperty("line.separator"));
                }

            }
            // 农历节日
            if (lunar.get("lunar_festival") != null) {
                String lunarFestival = (String) lunar.get("lunar_festival");
                if (!"".equals(lunarFestival)) {
                    contentBuffer.append("今天是农历节日：");
                    contentBuffer.append(lunarFestival);
                    contentBuffer.append(System.getProperty("line.separator"));
                }
            }
            // 节气
            if (lunar.get("jieqi") != null) {
                String jieqi = (String) lunar.get("jieqi");
                if (!"".equals(jieqi)) {
                    contentBuffer.append("是24节气中的：");
                    contentBuffer.append(jieqi);
                    contentBuffer.append("换节气适当增减衣服！");
                }
            }

            contentBuffer.append(System.getProperty("line.separator"));
            contentBuffer.append(System.getProperty("line.separator"));
        }


        // 下雨的时候
        weatherContext(contentBuffer, weatherInfo);
        return contentBuffer.toString();
    }

    /**
     * 生日提醒内容
     *
     * @param weatherInfo 天气预报
     * @return
     */
    private String getBirthdayContent(String weatherInfo) {
        String today = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD);
        int alreadyDays = DateUtils.betweenDays(remindBirthDay, today);
        // 加上周提示
        String alreadyWeeks = getWeeks(alreadyDays);

        StringBuffer contentBuffer = new StringBuffer("宝宝已经出生了");
        contentBuffer.append(alreadyDays);
        contentBuffer.append("天了(");
        contentBuffer.append(alreadyWeeks);
        contentBuffer.append(")");
        contentBuffer.append(System.getProperty("line.separator"));

        String content = getContent();
        if (content != null) {
            contentBuffer.append(System.getProperty("line.separator"));
            contentBuffer.append(System.getProperty("line.separator"));
            contentBuffer.append(content);
            contentBuffer.append(System.getProperty("line.separator"));
            contentBuffer.append(System.getProperty("line.separator"));
        }
        contentBuffer.append("照顾宝宝的同时，自己也要注意饮食、注意劳逸结合呦！");
        contentBuffer.append(System.getProperty("line.separator"));
        contentBuffer.append(System.getProperty("line.separator"));

        List<Map<String, Object>> lunarMap = txLunarService.getLunar(today);
        if (lunarMap != null && !lunarMap.isEmpty()) {
            Map<String, Object> lunar = lunarMap.get(0);
            // 国际节日
            if (lunar.get("festival") != null) {
                String festival = (String) lunar.get("festival");
                if (!"".equals(festival)) {
                    contentBuffer.append("今天是公历节日：");
                    contentBuffer.append(festival);
                    contentBuffer.append(System.getProperty("line.separator"));
                }

            }
            // 农历节日
            if (lunar.get("lunar_festival") != null) {
                String lunarFestival = (String) lunar.get("lunar_festival");
                if (!"".equals(lunarFestival)) {
                    contentBuffer.append("今天是农历节日：");
                    contentBuffer.append(lunarFestival);
                    contentBuffer.append(System.getProperty("line.separator"));
                }
            }
            // 节气
            if (lunar.get("jieqi") != null) {
                String jieqi = (String) lunar.get("jieqi");
                if (!"".equals(jieqi)) {
                    contentBuffer.append("是24节气中的：");
                    contentBuffer.append(jieqi);
                    contentBuffer.append("换节气适当增减衣服！");
                }
            }

            contentBuffer.append(System.getProperty("line.separator"));
            contentBuffer.append(System.getProperty("line.separator"));
        }


        // 下雨的时候
        weatherContext(contentBuffer, weatherInfo);

        return contentBuffer.toString();
    }

    /**
     * 下雨的时候天气提示
     *
     * @return
     */
    private void weatherContext(StringBuffer contentBuffer, String weatherInfo) {
        // 下雨的时候
        if (weatherInfo.contains("雨")) {
            contentBuffer.append("另外，今天");
            contentBuffer.append(weatherInfo);
            contentBuffer.append("，");
            contentBuffer.append("记得点餐定外卖哦！");
            // 空两行
            contentBuffer.append(System.getProperty("line.separator"));
            contentBuffer.append(System.getProperty("line.separator"));
        }
    }


    private String getWeeks(Integer days) {
        Map<String, Integer> alreadyDaysMap = DateUtils.betweenWeeks(days);
        String weeks = null;
        if (alreadyDaysMap.get(DateUtils.DAYS) != null) {
            weeks = alreadyDaysMap.get(DateUtils.WEEKS) + "周" + alreadyDaysMap.get("days") + "天";
        } else {
            weeks = alreadyDaysMap.get(DateUtils.WEEKS) + "周";
        }
        return weeks;
    }

    // 获取配置库中的邮件内容
    @Override
    public String getContent() {
        logger.info("获取配置库中的邮件内容 start...");
        // 获取今天之后的每一天
        List<String> list = DateUtils.getAfterDays(new Date(), 30);
        String dictType = "yoyo";
        String content = null;
        for (int i = 0; i < list.size(); i++) {
            Dict d = new Dict();
            d.setDictType(dictType);
            d.setDictCode(list.get(i));
            Dict dict = dictService.query(d);
            if (dict != null && i == 0) {
                content = "今天是咱们" + dictType + "的" + dict.getDictName();
            }
            if (dict != null && i != 0) {
                content = "距离咱们" + dictType + "的" + dict.getDictName() + "还有" + i + "天";
            }
        }
        logger.info("获取配置库中的邮件内容: {}", content);
        return content;
    }
}
