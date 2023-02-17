package com.data.chain.eventflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.base.ResponseVO;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.dto.FlowModeAddDto;
import com.data.chain.eventflow.dto.FlowModeNodeAddDto;
import com.data.chain.eventflow.dto.QueryFlowDto;
import com.data.chain.eventflow.entity.FlowModel;
import com.data.chain.eventflow.entity.FlowModelNode;
import com.data.chain.eventflow.vo.FlowModelVO;

import java.util.List;

/**
 * 流程模板主表(FlowModel)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 11:09:42
 */
public interface FlowModelService extends IService<FlowModel> {

    /**
     * 匹配流程模板关联流程配置节点数据
     * @return
     */
    FlowModelVO matchFlowModel(EventDTO eventDTO);

    /**
     * 分页查询流程配置列表
     *
     * @param dto 入参
     * @return 分页数据
     */
    IPage<FlowModel> queryList(QueryFlowDto dto);

    /**
     * 逻辑删除
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * 详情查询
     *
     * @param id id
     * @return 流程详情
     */
    FlowModelVO detail(Long id);

    /**
     * 保存流程模板
     *
     * @param dto 入参
     * @return 流程详情
     */
    FlowModel saveFlowMode(FlowModeAddDto dto);
}

