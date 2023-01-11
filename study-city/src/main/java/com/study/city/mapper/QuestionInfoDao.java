package com.study.city.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.study.city.entity.question.QuestionInfoDto;

/**
 * (QuestionInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-11 15:12:53
 */
public interface QuestionInfoDao extends BaseMapper<QuestionInfoDto> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<QuestionInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<QuestionInfoDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<QuestionInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<QuestionInfoDto> entities);

    // 分页查看题目
    List<QuestionInfoDto> getQuestions();


}

