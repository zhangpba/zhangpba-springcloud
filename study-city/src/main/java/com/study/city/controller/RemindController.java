package com.study.city.controller;

import com.study.city.service.IRemindService;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpba
 * @description 邮件提醒控制器
 * @date 2022/7/27
 */
@Api(value = "提醒服务", tags = "提醒服务")
@RestController
@RequestMapping("/remind")
public class RemindController {
    private static final Logger logger = LoggerFactory.getLogger(RemindController.class);

    @Autowired
    private IRemindService remindService;

    // 提醒订餐邮件需要发送的隐秘接收人
    @Value("${spring.mail.send.remind.switch}")
    private String remindSwitch;

    /**
     * 查询天气发送提醒订餐邮件
     *
     * @return
     */
    @ApiOperation(value = "查询天气发送提醒订餐邮件")
    @GetMapping(value = "/send")
    public ResponseMessage send() {
        logger.info("提醒订餐邮件的手动任务开始！");
        remindService.sendEmail();
        return ResponseMessage.success("发送成功！");
    }

    // 每天清晨六点发送提醒订餐邮件
    @ApiOperation(value = "定时发送提醒订餐邮件 cron = ${spring.mail.send.remind.syn-cron")
    @Scheduled(cron = "${spring.mail.send.remind.syn-cron}")    // 每天清晨10点01
    public void synRemind() {
        logger.info("提醒订餐邮件的定时任务开始！");
        if ("1".equals(remindSwitch)) {
            remindService.sendEmail();
        } else {
            logger.info("定时发送提醒订餐邮件 开关关闭 ...");
        }
        logger.info("定时发送提醒订餐邮件 end...");
    }
}
