package com.data.chain.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.data.chain.base.ResponseVO;
import com.data.chain.eventflow.dto.FlowModeAddDto;
import com.data.chain.eventflow.dto.FlowModeNodeAddDto;
import com.data.chain.eventflow.dto.QueryFlowDto;
import com.data.chain.eventflow.entity.FlowModel;
import com.data.chain.eventflow.entity.FlowModelNode;
import com.data.chain.eventflow.service.FlowModelNodeService;
import com.data.chain.eventflow.service.FlowModelService;
import com.data.chain.eventflow.vo.FlowModelNodeVO;
import com.data.chain.eventflow.vo.FlowModelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/15
 */
@RestController
@RequestMapping("admin/flowConfig")
@Api(tags = "流程配置")
@CrossOrigin
public class FlowConfigController {

    @Autowired
    private FlowModelService flowModelService;
    @Autowired
    private FlowModelNodeService flowModelNodeService;

    @PostMapping("queryList")
    @ApiOperation(value = "分页查询", response = FlowModel.class)
    public ResponseVO queryList(@RequestBody QueryFlowDto dto) {
        IPage<FlowModel> page = flowModelService.queryList(dto);
        return ResponseVO.success(page);
    }

    @PostMapping("saveFlowMode")
    @ApiOperation(value = "保存流程模板", response = FlowModel.class)
    public ResponseVO saveFlowMode(@RequestBody FlowModeAddDto dto) {
        FlowModel flowModel = flowModelService.saveFlowMode(dto);
        return ResponseVO.success(flowModel);
    }


    @PostMapping("saveFlowModeNode")
    @ApiOperation(value = "保存流程模板节点", response = FlowModelNode.class)
    public ResponseVO saveFlowModeNode(@RequestBody FlowModeNodeAddDto dto) {
        FlowModelNode flowModelNode = flowModelNodeService.saveNode(dto);
        return ResponseVO.success(flowModelNode);
    }

    @PostMapping("flowModeDelete/{id}")
    @ApiOperation(("流程模板删除"))
    public ResponseVO flowModeDelete(@PathVariable @ApiParam("流程模板id") Long id) {
        flowModelService.delete(id);
        return ResponseVO.success();
    }


    @PostMapping("ModeNodeDelete/{id}")
    @ApiOperation(("流程模板节点删除"))
    public ResponseVO ModeNodeDelete(@PathVariable @ApiParam("流程模板节点id") Long id) {
        flowModelNodeService.deleteNode(id);
        return ResponseVO.success();
    }

    @GetMapping("flowModeDetail/{id}")
    @ApiOperation(value = "流程模板详情查询", response = FlowModelVO.class)
    public ResponseVO flowModeDetail(@PathVariable @ApiParam("流程模板id") Long id) {
        FlowModelVO flowModel = flowModelService.detail(id);
        return ResponseVO.success(flowModel);
    }

    @GetMapping("ModeNodeDetail/{id}")
    @ApiOperation(value = "流程模板节点详情查询", response = FlowModelNodeVO.class)
    public ResponseVO ModeNodeDetail(@PathVariable @ApiParam("节点id") Long id) {
        FlowModelNodeVO flowModelNode = flowModelNodeService.detail(id);
        return ResponseVO.success(flowModelNode);
    }


}
