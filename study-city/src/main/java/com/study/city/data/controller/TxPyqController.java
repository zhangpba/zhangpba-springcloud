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
 * 天行数据-朋友圈文案
 *
 * @author zhangpba
 * @date 2022-05-11
 */
@Api(value = "天行数据-朋友圈文案", tags = "天行数据-朋友圈文案")
@RestController
@RequestMapping("/pyq")
public class TxPyqController {
    private static final Logger logger = LoggerFactory.getLogger(TxPyqController.class);

    @Autowired
    private ITxPyqService pyqService;

    // 朋友圈文案需要发送的人
    @Value("${spring.mail.send.pyq.switch}")
    private String pyqSwitch;

    /**
     * 获取朋友圈文案
     *
     * @return
     */
    @ApiOperation(value = "获取朋友圈文案")
    @GetMapping(value = "/getPyqWenan")
    public ResponseMessage getPyqWenan() {
        Map<String, String> map = pyqService.getPyqWenan();
        return ResponseMessage.success(map);
    }

    /**
     * 发送朋友圈文案
     *
     * @return
     */
    @ApiOperation(value = "发送朋友圈文案")
    @GetMapping(value = "/send")
    public ResponseMessage send() {
        pyqService.sendPyqEmail();
        return ResponseMessage.success("发送成功");
    }


    // 每天清晨六点发送朋友圈信息
    @ApiOperation(value = "定时发送朋友圈文案 cron = ${spring.mail.send.pyq.syn-cron")
    @Scheduled(cron = "${spring.mail.send.pyq.syn-cron}")    // 每天清晨六点
    public void synWeathers() {
        logger.info("定时发送朋友圈文案 start...");
        if ("1".equals(pyqSwitch)) {
            send();
        } else {
            logger.info("定时发送朋友圈文案 开关关闭 ...");
        }
        logger.info("定时发送朋友圈文案 end...");
    }
}
