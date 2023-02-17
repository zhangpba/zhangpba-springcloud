package com.data.chain.eventflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.eventflow.entity.EventNodeDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 事件处置流程节点明细表(EventNodeDetail)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:09:09
 */
@Mapper
public interface EventNodeDetailDao extends BaseMapper<EventNodeDetail> {

    Long insertEventNodeDetail(EventNodeDetail eventNodeDetail);

    int updateByPrimaryKeySelective(EventNodeDetail eventNodeDetail);

    int updatePreNext(@Param("list") List<EventNodeDetail> list );
}

