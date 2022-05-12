package com.study.city.controller;

import com.study.city.service.IPyqService;
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

import java.util.Map;


/**
 * 朋友圈文案
 *
 * @author zhangpba
 * @date 2022-05-11
 */
@Api(value = "朋友圈文案", tags = "朋友圈文案")
@RestController
@RequestMapping("/pyq")
public class PyqController {
    private static final Logger logger = LoggerFactory.getLogger(PyqController.class);

    @Autowired
    private IPyqService pyqService;

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
        pyqService.sendEmail();
        return ResponseMessage.success("发送成功");
    }

    // 每天清晨六点发送朋友圈信息
    @ApiOperation(value = "定时发送朋友圈文案 cron = ${module.pyq.syn-cron")
    @Scheduled(cron = "${module.pyq.syn-cron}")    // 每天清晨六点
    public void synWeathers() {
        logger.info("定时发送朋友圈文案 start...");
        send();
        logger.info("定时发送朋友圈文案 end...");
    }
}
