package com.data.chain.cardmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.FlowModelNode;
import com.data.chain.eventflow.vo.FlowModelVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程模板节点表(FlowModelNode)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:10:02
 */
@Mapper
public interface FlowModelNodeDao extends BaseMapper<FlowModelNode> {
    List<FlowModelNode> selectFlowModelNode(Long modelId);
}

