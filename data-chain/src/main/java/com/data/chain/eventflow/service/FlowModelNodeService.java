package com.data.chain.eventflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.chain.eventflow.dto.FlowModeNodeAddDto;
import com.data.chain.eventflow.entity.FlowModelNode;
import com.data.chain.eventflow.vo.FlowModelNodeVO;

/**
 * 流程模板节点表(FlowModelNode)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 11:10:02
 */
public interface FlowModelNodeService extends IService<FlowModelNode> {
    /**
     * 流程节点保存
     *
     * @param dto
     * @return 流程节点详情
     */
    FlowModelNode saveNode(FlowModeNodeAddDto dto);

    /**
     * 删除节点
     *
     * @param id id
     */
    void deleteNode(Long id);

    /**
     * 流程节点详情查询
     *
     * @param id id
     * @return 流程节点详情
     */
    FlowModelNodeVO detail(Long id);
}

