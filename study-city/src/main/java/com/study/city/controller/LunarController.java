package com.study.city.controller;

import com.study.city.service.ILunarService;
import com.study.starter.utils.DateUtils;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 中国老黄历
 *
 * @author zhangpba
 * @date 2022-05-13
 */
@Api(value = "中国老黄历", tags = "中国老黄历")
@RestController
@RequestMapping("/lunar")
public class LunarController {
    private static final Logger logger = LoggerFactory.getLogger(LunarController.class);

    @Autowired
    private ILunarService lunarService;

    // 老黄历需要接收的人
    @Value("${spring.mail.send.lunar.users}")
    private String lunarUsers;

    /**
     * 查询中国老黄历
     *
     * @param day 日期(格式：2022-01-01)
     * @return
     */
    @ApiOperation(value = "查询中国老黄历")
    @GetMapping(value = "/getCharacterByBrithday")
    public ResponseMessage getCharacterByBrithday(@ApiParam(name = "day", value = "日期(格式：2022-01-01)", required = true) @RequestParam String day) {
        logger.info("查询中国老黄历 日:{}", day);
        List<Map<String, Object>> mapList = lunarService.getLunar(day);
        logger.info("查询中国老黄历:{}", mapList);
        return ResponseMessage.success(mapList);
    }

    /**
     * 获取老黄历数据，并发送邮件
     * 0 30 1 * * * # 每天01：30执行一次
     */
    @Scheduled(cron = "${module.lunar.syn-cron}")
    public void synLunarData() {
        Date date = new Date();
        String dateStr = DateUtils.format(date, DateUtils.YYYY_MM_DD);
        sendLunarMail(dateStr, lunarUsers);
    }

    /**
     * 发送老黄历邮件
     *
     * @return
     */
    @ApiOperation(value = "发送老黄历邮件")
    @GetMapping(value = "/sendLunarMail")
    public ResponseMessage sendLunarMail(@ApiParam(name = "day", value = "日期(格式：2022-01-01)", required = true) @RequestParam String day,
                                         @ApiParam(name = "toUsers", value = "邮箱,多个邮箱需要用英文','连接", required = true) @RequestParam String toUsers) {
        logger.info("发送老黄历邮件 start...");
        try {
            lunarService.sendThymeleafMail(day, toUsers);
        } catch (MessagingException e) {
            logger.error("发送老黄历邮件失败：{}", e.getMessage());
            return ResponseMessage.success("发送老黄历邮件失败：" + e.getMessage());
        }
        return ResponseMessage.success("发送老黄历邮件成功！");
    }
}
