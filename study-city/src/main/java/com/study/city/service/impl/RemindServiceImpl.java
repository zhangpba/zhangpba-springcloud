package com.study.city.service.impl;

import com.study.city.entity.weather.Weather;
import com.study.city.service.IEmailService;
import com.study.city.service.IRemindService;
import com.study.city.service.IWeatherService;
import com.study.starter.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RemindServiceImpl implements IRemindService {
    private static final Logger logger = LoggerFactory.getLogger(RemindServiceImpl.class);

    @Autowired
    private IWeatherService weatherService;

    @Autowired
    private IEmailService emailService;

    // 朋友圈文案需要发送的人
    @Value("${spring.mail.send.remind.users}")
    private String remindBccUsers;

    @Override
    public void sendEmail() {
        String date = DateUtils.format(new Date(), DateUtils.YYYY_MM_DD);
        // 实时查询今天天气
        List<Weather> weatherList = weatherService.getWheatherByCity("西安");
        // 今天是否下雨
        boolean isRains = false;
        Weather weather = null;
        if (weatherList != null && !weatherList.isEmpty()) {
            for (Weather w : weatherList) {
                if (w.getDate().equals(date) && w.getType().contains("雨")) {
                    isRains = true;
                    weather = w;
                }
            }
        }

        // 发送提醒邮件
        if (isRains) {
            sendEmail(weather);
        }
    }

    /**
     * 发送普通邮件
     */
    private void sendEmail(Weather weather) {
        // 邮件主题
        String title = "订餐提醒";
        // 设置邮件的正文
        StringBuffer contentBuffer = new StringBuffer("亲，今天");
        contentBuffer.append(weather.getType());
        contentBuffer.append("，");
        contentBuffer.append("记得点餐定外卖哦！");
        // 空两行
        contentBuffer.append(System.getProperty("line.separator"));
        contentBuffer.append(System.getProperty("line.separator"));
        contentBuffer.append(weather.getWarn());
        emailService.sendEmail(title, contentBuffer.toString(), "", remindBccUsers);
    }
}
