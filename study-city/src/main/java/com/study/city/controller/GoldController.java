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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 黄金数据控制类
 *
 * @author zhangpba
 * @date 2022-04-16
 */
@Api(value = "黄金数据", tags = "黄金数据")
@RestController
@RequestMapping("/gold")
public class GoldController {

    private static final Logger logger = LoggerFactory.getLogger(GoldController.class);

    @Autowired
    private IGoldService goldService;

    @ApiOperation(value = "查询当日黄金")
    @GetMapping(value = "/getTodayGolds")
    public ResponseMessage getTodayGolds() {
        List<Gold> golds = goldService.getTodayGolds();
        return ResponseMessage.success(golds);
    }

    @ApiOperation(value = "查询历史-按照黄金类型分组")
    @GetMapping(value = "/getHistoryGolds")
    public ResponseMessage getHistoryGolds() {
        logger.info("全国各个城市区域数据入库 start...");
        Map<String, List<Gold>> golds = goldService.getHistoryGolds();
        return ResponseMessage.success(golds);
    }

    @CrossOrigin(origins = "*", maxAge = 3600) // 解决跨域问题
    @ApiOperation(value = "查询历史-黄金数组")
    @GetMapping(value = "/getHistoryGoldList")
    public ResponseMessage getHistoryGoldList() {
        logger.info("全国各个城市区域数据入库 start...");
        Map<String, List<Gold>> golds = goldService.getHistoryGolds();
        List<Gold> goldList = new ArrayList<>();
        for (String type : golds.keySet()) {
            goldList.addAll(golds.get(type));
        }
        return ResponseMessage.success(goldList);
    }

    @ApiOperation(value = "存储当日黄金")
    @GetMapping(value = "/saveTodayGold")
    public ResponseMessage saveTodayGold() {
        logger.info("存储当日黄金 start...");
        goldService.saveTodayGold();
        return ResponseMessage.success("存储当日黄金成功 ！");
    }

    @ApiOperation(value = "定时任务存储当日黄金 cron = ${module.gold.syn-cron")
    @Scheduled(cron = "${module.gold.syn-cron}")    // 每天23点30
    public void synWeathers() {
        logger.info("定时任务调度-黄金数据入库 start...");
        saveTodayGold();
        logger.info("定时任务调度-黄金数据入库 end...");
    }
}
