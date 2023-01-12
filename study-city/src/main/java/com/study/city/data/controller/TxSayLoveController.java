package com.study.city.data.controller;

import com.study.city.data.service.ITxPyqService;
import com.study.common.web.ResponseMessage;
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

import java.util.Map;


/**
 * 土味情话
 *
 * @author zhangpba
 * @date 2022-05-13
 */
@Api(value = "土味情话", tags = "土味情话")
@RestController
@RequestMapping("/saylove")
public class TxSayLoveController {
    private static final Logger logger = LoggerFactory.getLogger(TxSayLoveController.class);

    @Autowired
    private ITxPyqService pyqService;

    // 土味情话需要发送的人
    @Value("${spring.mail.send.say-love.switch}")
    private String sayLoveSwitch;

    /**
     * 获取土味情话
     *
     * @return
     */
    @ApiOperation(value = "获取土味情话")
    @GetMapping(value = "/getPyqWenan")
    public ResponseMessage getPyqWenan() {
        Map<String, String> map = pyqService.getSayLove();
        return ResponseMessage.success(map);
    }

    /**
     * 发送土味情话邮件成功
     *
     * @return
     */
    @ApiOperation(value = "发送土味情话邮件成功")
    @GetMapping(value = "/send")
    public ResponseMessage send() {
        pyqService.sendSayLoveEmail();
        return ResponseMessage.success("发送土味情话邮件成功");
    }


    // 每天清晨六点发送土味情话
    @ApiOperation(value = "发送土味情话邮件 cron = ${spring.mail.send.say-love.syn-cron")
    @Scheduled(cron = "${spring.mail.send.say-love.syn-cron}")    // 每天清晨六点
    public void synSayLove() {
        logger.info("定时发送土味情话邮件 start...");
        // 开关关闭不发送邮件
        if ("1".equals(sayLoveSwitch)) {
            send();
        } else {
            logger.info("定时发送土味情话 开关关闭 ...");
        }
        logger.info("定时发送土味情话邮件 end...");
    }
}
