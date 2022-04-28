package com.study.city.controller;

import com.study.city.entity.gold.Gold;
import com.study.city.entity.gold.GoldBase;
import com.study.city.enums.GoldEnum;
import com.study.city.service.IGoldService;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 查询当日黄金
     *
     * @param exchangeType 黄金枚举类
     * @return
     */
    @ApiOperation(value = "查询当日黄金")
    @GetMapping(value = "/getTodayGolds")
    public ResponseMessage getTodayGolds(@ApiParam(name = "exchangeType", value = "交易机构", required = true) @RequestParam String exchangeType) {
        if (!GoldEnum.contains(exchangeType)) {
            return ResponseMessage.error("交易机构类型不存在");
        }
        List<Gold> golds = goldService.getTodayGolds(exchangeType);
        return ResponseMessage.success(golds);
    }

    @ApiOperation(value = "存储当日黄金")
    @GetMapping(value = "/saveTodayGold")
    public ResponseMessage saveTodayGold() {
        logger.info("存储当日黄金 start...");
        goldService.saveTodayGold();
        return ResponseMessage.success("存储当日黄金成功 ！");
    }

    @ApiOperation(value = "查询历史-按照交易机构分组")
    @GetMapping(value = "/getHistoryGroupByExchangeType")
    public ResponseMessage getHistoryGroupByExchangeType() {
        logger.info("查询历史-按照交易机构分组 start...");
        Map<String, List<Gold>> golds = goldService.getHistoryGolds();
        return ResponseMessage.success(golds);
    }

    @ApiOperation(value = "查询历史-黄金数组")
    @GetMapping(value = "/getHistoryGoldList")
    public ResponseMessage getHistoryGoldList() {
        logger.info("查询历史-黄金数组 start...");
        Map<String, List<Gold>> golds = goldService.getHistoryGolds();
        List<Gold> goldList = new ArrayList<>();
        for (String type : golds.keySet()) {
            goldList.addAll(golds.get(type));
        }
        return ResponseMessage.success(goldList);
    }


    @ApiOperation(value = "查询历史黄金数据")
    @GetMapping(value = "/queryHistory")
    public ResponseMessage queryHistory(@ApiParam(name = "exchangeType", value = "交易机构:[shgold,hkgold,bank,london]", required = true) @RequestParam(required = true) String exchangeType,
                                        @ApiParam(name = "startDate", value = "开始时间", required = false) @RequestParam(required = false) String startDate,
                                        @ApiParam(name = "endDate", value = "结束时间", required = false) @RequestParam(required = false) String endDate,
                                        @ApiParam(name = "type", value = "黄金类型", required = false) @RequestParam(required = false) String type) {
        logger.info("查询历史黄金数据...");
        List<Gold> list = goldService.getGoldHistory(exchangeType, startDate, endDate, type, null);
        List<GoldBase> goldBases = goldService.toGoldList(list, exchangeType);
        return ResponseMessage.success(goldBases);
    }

    @ApiOperation(value = "查询历史-上海黄金")
    @GetMapping(value = "/getHistoryShGoldsList")
    public ResponseMessage getHistoryShGoldsList() {
        logger.info("查询历史-上海黄金 start...");
        List<Gold> list = goldService.getGoldHistory(GoldEnum.SHGOLD.getExchangeType(), null, null, "Au(T+D)", null);
        return ResponseMessage.success(list);
    }

    @ApiOperation(value = "查询历史-伦敦黄金")
    @GetMapping(value = "/getHistoryLdGoldsList")
    public ResponseMessage getHistoryLdGoldsList() {
        logger.info("查询历史-伦敦黄金 start...");
        List<Gold> list = goldService.getGoldHistory(GoldEnum.LONDON.getExchangeType(), null, null, "伦敦金", null);
        return ResponseMessage.success(list);
    }

    @ApiOperation(value = "查询历史-香港黄金")
    @GetMapping(value = "/getHistoryHkGoldsList")
    public ResponseMessage getHistoryHkGoldsList() {
        logger.info("查询历史-香港黄金 start...");
        List<Gold> list = goldService.getGoldHistory(GoldEnum.HKGOLD.getExchangeType(), null, null, "公斤条", null);
        return ResponseMessage.success(list);
    }

    @ApiOperation(value = "查询历史-银行黄金")
    @GetMapping(value = "/getHistoryBankGoldsList")
    public ResponseMessage getHistoryBankGoldsList() {
        logger.info("查询历史-银行黄金 start...");
        List<Gold> list = goldService.getGoldHistory(GoldEnum.BANK.getExchangeType(), null, null, null, "人民币黄金");
        return ResponseMessage.success(list);
    }

    @ApiOperation(value = "定时任务存储当日黄金 cron = ${module.gold.syn-cron")
    @Scheduled(cron = "${module.gold.syn-cron}")    // 每天23点30
    public void synWeathers() {
        logger.info("定时任务调度-黄金数据入库 start...");
        saveTodayGold();
        logger.info("定时任务调度-黄金数据入库 end...");
    }
}
