package com.study.city.exam.mapper;

import com.study.city.exam.entity.ExamQuestionInfo;
import com.study.city.exam.entity.request.ExamQuestionInfoRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ExamQuestionInfo)表数据库访问层
 *
 * @author zhanpba
 * @since 2023-01-13 16:57:29
 */
public interface ExamQuestionInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamQuestionInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param examQuestionInfoRequest 查询条件
     * @return 对象列表
     */
    List<ExamQuestionInfo> queryAll(ExamQuestionInfoRequest examQuestionInfoRequest);

    /**
     * 统计总行数
     *
     * @param examQuestionInfo 查询条件
     * @return 总行数
     */
    long count(ExamQuestionInfo examQuestionInfo);

    /**
     * 新增数据
     *
     * @param examQuestionInfo 实例对象
     * @return 影响行数
     */
    int insert(ExamQuestionInfo examQuestionInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamQuestionInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExamQuestionInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamQuestionInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExamQuestionInfo> entities);

    /**
     * 修改数据
     *
     * @param examQuestionInfo 实例对象
     * @return 影响行数
     */
    int update(ExamQuestionInfo examQuestionInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

