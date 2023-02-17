package com.data.chain.eventflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.eventflow.entity.Event;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警事件表，接收到预警信息后创建一条预警事件，匹配相应的流程按节点处置。(Event)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:07:27
 */
@Mapper
public interface EventDao extends BaseMapper<Event> {

    Long saveEvent(Event entity);

}

