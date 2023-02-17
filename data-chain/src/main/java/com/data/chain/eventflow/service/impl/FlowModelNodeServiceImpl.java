package com.data.chain.eventflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.cardmanage.dao.FlowModelNodeDao;
import com.data.chain.cardmanage.service.MonitorRelationService;
import com.data.chain.enums.IsDeleteEnum;
import com.data.chain.eventflow.dto.FlowModeNodeAddDto;
import com.data.chain.eventflow.entity.FlowModelNode;
import com.data.chain.eventflow.service.FlowModelNodeService;
import com.data.chain.eventflow.vo.FlowModelNodeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 流程模板节点表(FlowModelNode)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 11:10:02
 */
@Service
public class FlowModelNodeServiceImpl extends ServiceImpl<FlowModelNodeDao, FlowModelNode> implements FlowModelNodeService {

    @Autowired
    private MonitorRelationService monitorRelationService;
    @Override
    public FlowModelNode saveNode(FlowModeNodeAddDto dto) {
        FlowModelNode modelNode = dto.convert();
        this.saveOrUpdate(modelNode);
        if (Objects.nonNull(dto.getPreNodeId()) && 0 != dto.getPreNodeId()) {
            FlowModelNode flowModelNode = new FlowModelNode();
            flowModelNode.setId(dto.getPreNodeId());
            flowModelNode.setNextNodeId(modelNode.getId());
            this.updateById(flowModelNode);
        }
        return modelNode;
    }

    @Override
    public void deleteNode(Long id) {
        FlowModelNode modelNode = this.getById(id);
        modelNode.setIsDelete(IsDeleteEnum.DELETED.getStatus());
        this.updateById(modelNode);
        List<FlowModelNode> list = this.lambdaQuery()
                .eq(FlowModelNode::getIsDelete, IsDeleteEnum.NORMAL.getStatus())
                .and(wrapper -> wrapper.eq(FlowModelNode::getPreNodeId, id).or().eq(FlowModelNode::getNextNodeId, id))
                .list();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        FlowModelNode preNode = null;
        FlowModelNode nextNode = null;
        for (FlowModelNode item : list) {
            // 存在上个节点
            if (id.equals(item.getNextNodeId())) {
                preNode = item;
            }
            // 存在下个节点
            if (id.equals(item.getPreNodeId())) {
                nextNode = item;
            }
        }
        if (null != preNode && null != nextNode) {
            preNode.setNextNodeId(nextNode.getId());
            nextNode.setPreNodeId(preNode.getId());
        }
        if (null != preNode && null == nextNode) {
            preNode.setNextNodeId(0L);
        }
        if (null == preNode && null != nextNode) {
            nextNode.setPreNodeId(0L);
        }
        if (null != preNode) {
            this.updateById(preNode);
        }
        if (null != nextNode) {
            this.updateById(nextNode);
        }
    }

    @Override
    public FlowModelNodeVO detail(Long id) {
        FlowModelNode flowModelNode = this.getById(id);
        FlowModelNodeVO flowModelNodeVO = new FlowModelNodeVO();
        BeanUtils.copyProperties(flowModelNode, flowModelNodeVO);
        return flowModelNodeVO;
    }
}

