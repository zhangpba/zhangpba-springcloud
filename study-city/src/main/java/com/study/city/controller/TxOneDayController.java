package com.study.city.controller;

import com.study.city.entity.tianxing.OneDay;
import com.study.city.service.ITxOneDayService;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 每日一小句
 *
 * @author zhangpba
 * @date 2022-05-11
 */
@Api(value = "每日一小句", tags = "每日一小句")
@RestController
@RequestMapping("/one-day")
public class TxOneDayController {
    private static final Logger logger = LoggerFactory.getLogger(TxOneDayController.class);

    @Autowired
    private ITxOneDayService oneDayService;

    /**
     * 获取每日一小句
     *
     * @return
     */
    @ApiOperation(value = "获取每日一小句")
    @GetMapping(value = "/getOneDay")
    public ResponseMessage getOneDay() {
        OneDay oneDay = oneDayService.getOneDay();
        return ResponseMessage.success(oneDay);
    }

    /**
     * 发送每日一小句
     *
     * @return
     */
    @ApiOperation(value = "发送每日一小句")
    @GetMapping(value = "/send")
    public ResponseMessage send() {
        oneDayService.sendOneDayEmail();
        return ResponseMessage.success("发送每日一小句成功");
    }


    // 定时发送每日一小句
    @ApiOperation(value = "定时发送每日一小句 cron = ${module.one-day.syn-cron")
    @Scheduled(cron = "${module.one-day.syn-cron}")    // 每天清晨六点
    public void synWeathers() {
        logger.info("定时发送每日一小句 start...");
        send();
        logger.info("定时发送每日一小句 end...");
    }
}
