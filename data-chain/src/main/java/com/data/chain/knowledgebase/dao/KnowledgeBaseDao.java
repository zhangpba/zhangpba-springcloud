package com.data.chain.knowledgebase.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.knowledgebase.entity.KnowledgeBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 知识库表(KnowledgeBase)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:10:20
 */
@Mapper
public interface KnowledgeBaseDao extends BaseMapper<KnowledgeBase> {

    List<KnowledgeBase> searchReceiverList(@Param("orgList") List orgList, @Param("operatorTypeList") List operatorTypeList);

    List<KnowledgeBase> getOperatorByPhone(@Param("phone") String phone);
}

