package com.data.chain.cardmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.dto.MonitorRecordDto;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.service.CardManageService;
import com.data.chain.cardmanage.service.CardTaskService;
import com.data.chain.cardmanage.vo.MonitorBusinessVO;
import com.data.chain.cardmanage.vo.QueryCardTaskVO;
import com.data.chain.eventflow.entity.EventNodeDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/13
 */
@RestController
@RequestMapping("cardManage")
@Api(tags = "卡片管理")
public class CardManageController {

    @Autowired
    private CardManageService cardManageService;
    @Autowired
    private CardTaskService cardTaskService;

    @GetMapping("myTask")
    @ApiOperation(value = "我的任务列表", response = CardTask.class)
    public ResponseVO myTask(@ApiParam("0未开始，1已开始，2已结束") @RequestParam Integer status, String userId, Integer pageNum, Integer pageSize) {
        if (StringUtils.isEmpty(pageNum)) pageNum = 1;
        if (StringUtils.isEmpty(pageSize)) pageSize = 10;
        IPage<QueryCardTaskVO> result = cardManageService.myTask(status, userId, pageNum, pageSize);
        return ResponseVO.success(result);
    }

    @PostMapping("accept")
    @ApiOperation("接受")
    public ResponseVO<Boolean> accept(@ApiParam("卡片任务id")@RequestParam Long cardTaskId,
                             @ApiParam("用户id")@RequestParam String userId) {
        cardManageService.accept(cardTaskId, userId);
        return ResponseVO.success();
    }

    @PostMapping("handle")
    @ApiOperation("完成")
    public ResponseVO<Boolean> handle(@ApiParam("卡片任务id")@RequestParam Long cardTaskId,
                             @ApiParam("用户id")@RequestParam String userId) {
        cardManageService.handle(cardTaskId, userId);
        return ResponseVO.success();
    }

    @PostMapping("reassign")
    @ApiOperation("转交")
    public ResponseVO reassign(Long cardTaskId, String phone) {
        cardManageService.reassign(cardTaskId, phone);
        return ResponseVO.success();
    }

    @GetMapping("detail")
    @ApiOperation(value = "卡片详情", response = QueryCardTaskVO.class)
    public ResponseVO detail(Long cardTaskId)  {
        QueryCardTaskVO vo = cardManageService.detail(cardTaskId);
        // 如果查看时间为空，更新查看时间
        if (vo.getViewTime() == null){
            CardTask cardTask = new CardTask();
            cardTask.setId(cardTaskId);
            cardTask.setViewTime(new Date());
            cardTaskService.updateById(cardTask);
        }
        return ResponseVO.success(vo);
    }

    @GetMapping("detailByNodeId")
    @ApiOperation(value = "卡片详情-节点id and 用户id 查询", response = QueryCardTaskVO.class)
    public ResponseVO detailByNodeId(Long nodeDetailId, String userId)  {
        QueryCardTaskVO vo = cardManageService.detail(nodeDetailId, userId);
        return ResponseVO.success(vo);
    }

    @GetMapping("flowQuery")
    @ApiOperation(value = "流程查询", response = EventNodeDetail.class)
    public ResponseVO flowQuery(Long nodeDetailId) {
        List<EventNodeDetail> result = cardManageService.flowQuery(nodeDetailId);
        return ResponseVO.success(result);
    }

    @GetMapping("monitorBusinessList")
    @ApiOperation(value = "督导业务列表", response = MonitorBusinessVO.class)
    public ResponseVO monitorBusinessList(Long nodeDetailId, Long cardTaskId) {
        List<MonitorBusinessVO> vos = cardManageService.monitorBusinessList(nodeDetailId, cardTaskId);
        return ResponseVO.success(vos);
    }

    @PostMapping("submitMonitorOpinions")
    @ApiOperation(value = "提交督导意见")
    public ResponseVO submitMonitorOpinions(@RequestBody MonitorRecordDto dto) {
        cardManageService.submitMonitorOpinions(dto);
        return ResponseVO.success();
    }

    @GetMapping("monitorBusinessDetail")
    @ApiOperation(value = "督导业务详情", response = MonitorBusinessVO.class)
    public ResponseVO monitorBusinessDetail(Long nodeDetailId, Long cardTaskId) {
        MonitorBusinessVO vo = cardManageService.monitorBusinessDetail(nodeDetailId, cardTaskId);
        return ResponseVO.success(vo);
    }
}
