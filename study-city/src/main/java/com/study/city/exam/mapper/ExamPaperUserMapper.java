package com.study.city.exam.mapper;

import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ExamPaperUser)表数据库访问层
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
public interface ExamPaperUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamPaperUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param examPaperUserListRequest 查询条件
     * @return 对象列表
     */
    List<ExamPaperUser> queryAll(ExamPaperUserListRequest examPaperUserListRequest);

    /**
     * 统计总行数
     *
     * @param examPaperUser 查询条件
     * @return 总行数
     */
    long count(ExamPaperUser examPaperUser);

    /**
     * 新增数据
     *
     * @param examPaperUser 实例对象
     * @return 影响行数
     */
    int insert(ExamPaperUser examPaperUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamPaperUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExamPaperUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExamPaperUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExamPaperUser> entities);

    /**
     * 修改数据
     *
     * @param examPaperUser 实例对象
     * @return 影响行数
     */
    int update(ExamPaperUser examPaperUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

