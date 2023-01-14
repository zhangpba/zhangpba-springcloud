package com.study.city.exam.mapper;

import com.study.city.exam.entity.ExamPaper;
import com.study.city.exam.entity.request.ExamPaperListRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ExamPaper)表数据库访问层
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:20
 */
public interface ExamPaperMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamPaper queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param examPaperRequest 查询条件
     * @return 对象列表
     */
    List<ExamPaper> queryAll(ExamPaperListRequest examPaperRequest);

    /**
     * 统计总行数
     *
     * @param examPaper 查询条件
     * @return 总行数
     */
    long count(ExamPaper examPaper);

    /**
     * 新增数据
     *
     * @param examPaper 实例对象
     * @return 影响行数
     */
    int insert(ExamPaper examPaper);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamPaper> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExamPaper> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamPaper> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExamPaper> entities);

    /**
     * 修改数据
     *
     * @param examPaper 实例对象
     * @return 影响行数
     */
    int update(ExamPaper examPaper);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

