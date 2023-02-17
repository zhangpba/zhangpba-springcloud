package com.data.chain.eventflow.controller;

import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.dao.CardTaskDao;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.service.CardTaskService;
import com.data.chain.cardmanage.vo.CardTaskVO;
import com.data.chain.eventflow.entity.Event;
import com.data.chain.eventflow.entity.EventNodeDetail;
import com.data.chain.eventflow.service.EventNodeDetailService;
import com.data.chain.eventflow.service.EventService;
import com.data.chain.eventflow.vo.FlowModelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("event")
@Api(tags = "事件查询")
public class EventFlowController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventNodeDetailService eventNodeDetailService;
    @Autowired
    private CardTaskDao cardTaskDao;

    @GetMapping("queryEventByUnicode")
    @ApiOperation(value = "事件查询")
    public ResponseVO queryEvent(@RequestParam String unicode) {
        Event one = eventService.lambdaQuery().eq(Event::getUniqueCode, unicode).one();
        return ResponseVO.success(one);
    }
    @GetMapping("queryCardByUnicode")
    @ApiOperation(value = "卡片查询")
    public ResponseVO queryCard(@RequestParam String unicode) {
        List<CardTaskVO> cardTasks = cardTaskDao.queryByUnicode(unicode);
        return ResponseVO.success(cardTasks);
    }

}
