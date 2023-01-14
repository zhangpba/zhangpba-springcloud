package com.study.city.exam.service;

import com.study.city.exam.entity.ExamQuestionOptions;

import java.util.List;

/**
 * (ExamQuestionOptions)表服务接口
 *
 * @author zhanpba
 * @since 2023-01-13 17:02:56
 */
public interface ExamQuestionOptionsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamQuestionOptions queryById(Integer id);

    /**
     * 分页查询
     *
     * @param examQuestionOptions 筛选条件
     * @return 查询结果
     */
    List<ExamQuestionOptions> queryExamQuestionOptions(ExamQuestionOptions examQuestionOptions);

    /**
     * 新增数据
     *
     * @param examQuestionOptions 实例对象
     * @return 实例对象
     */
    ExamQuestionOptions insert(ExamQuestionOptions examQuestionOptions);

    /**
     * 修改数据
     *
     * @param examQuestionOptions 实例对象
     * @return 实例对象
     */
    ExamQuestionOptions update(ExamQuestionOptions examQuestionOptions);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
