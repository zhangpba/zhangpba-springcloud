package com.study.city.exam.mapper;

import com.study.city.exam.entity.ExamPaperDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ExamPaperDetail)表数据库访问层
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:46
 */
public interface ExamPaperDetailMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamPaperDetail queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param examPaperDetail 查询条件
     * @return 对象列表
     */
    List<ExamPaperDetail> queryAll(ExamPaperDetail examPaperDetail);

    /**
     * 统计总行数
     *
     * @param examPaperDetail 查询条件
     * @return 总行数
     */
    long count(ExamPaperDetail examPaperDetail);

    /**
     * 新增数据
     *
     * @param examPaperDetail 实例对象
     * @return 影响行数
     */
    int insert(ExamPaperDetail examPaperDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamPaperDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExamPaperDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamPaperDetail> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExamPaperDetail> entities);

    /**
     * 修改数据
     *
     * @param examPaperDetail 实例对象
     * @return 影响行数
     */
    int update(ExamPaperDetail examPaperDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    // 查询在页面显示的试卷题目
//    queryExamPaperUser();

}

