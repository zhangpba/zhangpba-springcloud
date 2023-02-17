package com.data.chain.cardmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.cardmanage.entity.MonitorRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 节点明细的督导关系表(MonitorRelation)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:11:09
 */
@Mapper
public interface MonitorRelationDao extends BaseMapper<MonitorRelation> {

}

