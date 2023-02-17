package com.data.chain.eventflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.eventflow.entity.EventParams;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件参数表(EventParams)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:08:40
 */
@Mapper
public interface EventParamsDao extends BaseMapper<EventParams> {

}

