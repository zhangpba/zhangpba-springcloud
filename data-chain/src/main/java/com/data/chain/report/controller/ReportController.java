package com.data.chain.report.controller;

import com.data.chain.base.ResponseVO;
import com.data.chain.eventflow.enums.BusinessTypeEnum;
import com.data.chain.report.service.IReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpba
 * @description 报表控制器
 * @date 2023/1/4
 */
@EnableScheduling
@RestController
@RequestMapping("report")
@Api(tags = "报表")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private IReportService reportService;

    @ApiOperation(value = "总体数据")
    @GetMapping(value = "/total")
    public ResponseVO weekTotal() {
        Map<String, Object> map = reportService.weekTotal();
        return ResponseVO.success(map);
    }

    @ApiOperation(value = "本周推送消息数量")
    @GetMapping(value = "/weekMessages")
    public ResponseVO weekMessages() {
        Map<String, Object> map = reportService.weekMessages();

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map01 = new HashMap<>();
        Map<String, Object> map02 = new HashMap<>();
        Map<String, Object> map03 = new HashMap<>();
        Map<String, Object> map04 = new HashMap<>();
        Map<String, Object> map05 = new HashMap<>();
        Map<String, Object> map06 = new HashMap<>();
        Map<String, Object> map07 = new HashMap<>();

        for (String s : map.keySet()) {
            if ("星期一01".equals(s)) {
                map01.put("day", "星期一");
                map01.put("warn", map.get(s));
            }
            if ("星期二01".equals(s)) {
                map02.put("day", "星期二");
                map02.put("warn", map.get(s));
            }
            if ("星期三01".equals(s)) {
                map03.put("day", "星期三");
                map03.put("warn", map.get(s));
            }
            if ("星期四01".equals(s)) {
                map04.put("day", "星期四");
                map04.put("warn", map.get(s));
            }
            if ("星期五01".equals(s)) {
                map05.put("day", "星期五");
                map05.put("warn", map.get(s));
            }
            if ("星期六01".equals(s)) {
                map06.put("day", "星期六");
                map06.put("warn", map.get(s));
            }
            if ("星期日01".equals(s)) {
                map07.put("day", "星期日");
                map07.put("warn", map.get(s));
            }
            if ("星期一02".equals(s)) {
                map01.put("notice", map.get(s));
            }
            if ("星期二02".equals(s)) {
                map02.put("notice", map.get(s));
            }
            if ("星期三02".equals(s)) {
                map03.put("notice", map.get(s));
            }
            if ("星期四02".equals(s)) {
                map04.put("notice", map.get(s));
            }
            if ("星期五02".equals(s)) {
                map05.put("notice", map.get(s));
            }
            if ("星期六02".equals(s)) {
                map06.put("notice", map.get(s));
            }
            if ("星期日02".equals(s)) {
                map07.put("warn", map.get(s));
            }
        }

        list.add(map01);
        list.add(map02);
        list.add(map03);
        list.add(map04);
        list.add(map05);
        list.add(map06);
        list.add(map07);
        return ResponseVO.success(list);
    }

    @ApiOperation(value = "昨日推送平均用时")
    @GetMapping(value = "/yesterdayPushTime")
    public ResponseVO yesterdayPushTime() {
        Map<String, Object> map = reportService.yesterdayPushTime();
        List<Map<String, Object>> list = new ArrayList<>();
        Map handMap = new HashMap<>();
        handMap.put("TYPE", "处置时间");
        Map acceptMap = new HashMap<>();
        acceptMap.put("TYPE", "接收时间");
        Map lookMap = new HashMap<>();
        lookMap.put("TYPE", "查看时间");
        for (String key : map.keySet()) {
            if ("WARN_HANDLE_NUM".equals(key)) {
                handMap.put("WARN", map.get("WARN_HANDLE_NUM"));
            } else if ("WARN_ACCEPT_NUM".equals(key)) {
                acceptMap.put("WARN", map.get("WARN_ACCEPT_NUM"));
            } else if ("WARN_LOOK_NUM".equals(key)) {
                lookMap.put("WARN", map.get("WARN_LOOK_NUM"));
            } else if ("NOTICE_HANDLE_NUM".equals(key)) {
                handMap.put("NOTICE", map.get("NOTICE_HANDLE_NUM"));
            } else if ("NOTICE_ACCEPT_NUM".equals(key)) {
                acceptMap.put("NOTICE", map.get("NOTICE_ACCEPT_NUM"));
            } else if ("NOTICE_LOOK_NUM".equals(key)) {
                lookMap.put("NOTICE", map.get("NOTICE_LOOK_NUM"));
            }
        }
        list.add(handMap);
        list.add(acceptMap);
        list.add(lookMap);
        return ResponseVO.success(list);
    }

    @ApiOperation(value = "当日推送消息分类统计")
    @GetMapping(value = "/totdayPushType")
    public ResponseVO totdayPushType() {
        List<Map<String, Object>> list = reportService.totdayPushType();
        for (Map<String, Object> map : list) {
            if (BusinessTypeEnum.CH4_RISK.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.CH4_RISK.getDesc());
            }
            if (BusinessTypeEnum.CO_RISK.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.CO_RISK.getDesc());
            }
            if (BusinessTypeEnum.DATA_INTERRUPT_1H.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.DATA_INTERRUPT_1H.getDesc());
            }
            if (BusinessTypeEnum.MINE_OVERMAN.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.MINE_OVERMAN.getDesc());
            }
            if (BusinessTypeEnum.CH4_MUTATION.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.CH4_MUTATION.getDesc());
            }
            if (BusinessTypeEnum.CO_MUTATION.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.CO_MUTATION.getDesc());
            }
            if (BusinessTypeEnum.ON_DUTY_VACANCY.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.ON_DUTY_VACANCY.getDesc());
            }
            if (BusinessTypeEnum.ILLEGAL_PRODUCE_ANALYSIS.getType().equals(map.get("businessType"))) {
                map.put("businessType", BusinessTypeEnum.ILLEGAL_PRODUCE_ANALYSIS.getDesc());
            }

        }
        return ResponseVO.success(list);
    }

    @ApiOperation(value = "当日企业接收信息数量TOP10")
    @GetMapping(value = "/todayReciveNum")
    public ResponseVO todayReciveNum() {
        List<Map<String, Object>> map = reportService.todayReciveNum();
        return ResponseVO.success(map);
    }

    @ApiOperation(value = "当日企业处置平均用时TOP10")
    @GetMapping(value = "/todayHandleTime")
    public ResponseVO todayHandleTime() {
        List<Map<String, Object>> list = reportService.todayHandleTime();
        return ResponseVO.success(list);
    }

    @ApiOperation(value = "本周企业超时处置TOP10-构造")
    @GetMapping(value = "/weekOverTime")
    public ResponseVO weekOverTime() {
        List<Map<String, Object>> list = reportService.weekOverTime();
        return ResponseVO.success(list);
    }


    /**
     * 报表定时任务：根据业务类型字段完成对应的数据分类统计。  人个数、 处置状态。
     */
//    @Scheduled(cron = "*/5 * * * * *")  // 每隔5秒执行一次
    public void synWeathers() {
        logger.info("根据业务类型字段完成对应的数据分类统计 start...");

        logger.info("根据业务类型字段完成对应的数据分类统计 end...");
    }

}
