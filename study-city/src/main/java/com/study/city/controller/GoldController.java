package com.study.city.controller;

import com.study.city.entity.Gold;
import com.study.city.service.IGoldService;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 黄金数据控制类
 *
 * @author zhangpba
 * @date 2022-04-16
 */
@Api(value = "黄金数据")
@RestController
public class GoldController {

    private static final Logger logger = LoggerFactory.getLogger(GoldController.class);

    @Autowired
    private IGoldService goldService;

    @ApiOperation(value = "获取黄金数据")
    @GetMapping(value = "/getGold")
    public ResponseMessage getGold() {
        logger.info("全国各个城市区域数据入库 start...");
        List<Gold> golds = goldService.getGoldList();
        return ResponseMessage.success(golds);
    }

    @ApiOperation(value = "存储黄金数据")
    @GetMapping(value = "/saveGold")
    public ResponseMessage saveGold() {
        logger.info("黄金数据入库 start...");
        String result = goldService.saveAllGolds();
        return ResponseMessage.success(result);
    }

    @ApiOperation(value = "定时任务存储黄金数据 cron = ${module.gold.syn-cron")
    @Scheduled(cron = "${module.gold.syn-cron}")    // 每天23点30
    public void synWeathers() {
        logger.info("定时任务调度-黄金数据入库 start...");
        saveGold();
        logger.info("定时任务调度-黄金数据入库 end...");
    }
}
