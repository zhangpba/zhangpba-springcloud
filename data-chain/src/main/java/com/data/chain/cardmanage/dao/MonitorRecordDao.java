package com.data.chain.cardmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.cardmanage.entity.MonitorRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 督导记录表(MonitorRecord)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:10:44
 */
@Mapper
public interface MonitorRecordDao extends BaseMapper<MonitorRecord> {

}

