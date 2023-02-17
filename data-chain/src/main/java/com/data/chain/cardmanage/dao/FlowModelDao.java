package com.data.chain.cardmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.eventflow.dto.EventDTO;
import com.data.chain.eventflow.entity.FlowModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程模板主表(FlowModel)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:09:42
 */
@Mapper
public interface FlowModelDao extends BaseMapper<FlowModel> {

    List<FlowModel> selectFlowModelByEvent(@Param("eventDTO") EventDTO eventDTO);
}

