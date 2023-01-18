package com.study.city.exam.service;

import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamQuestionInfo;
import com.study.city.exam.entity.request.ExamQuestionInfoRequest;

/**
 * (ExamQuestionInfo)表服务接口
 *
 * @author zhanpba
 * @since 2023-01-13 16:57:31
 */
public interface IExamQuestionInfoService {

    /**
     * 根据ID同步题目
     *
     * @param id
     */
    void synQuestionInfo(Integer id);

    /**
     * 同步所有的题目
     */
    void synQuestionInfoAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamQuestionInfo queryById(Integer id);

    /**
     * 分页查询
     *
     * @param examQuestionInfoRequest 筛选条件
     * @return 查询结果
     */
    PageInfo<ExamQuestionInfo> queryByPage(ExamQuestionInfoRequest examQuestionInfoRequest);

    /**
     * 新增数据
     *
     * @param examQuestionInfo 实例对象
     * @return 实例对象
     */
    ExamQuestionInfo insert(ExamQuestionInfo examQuestionInfo);

    /**
     * 修改数据
     *
     * @param examQuestionInfo 实例对象
     * @return 实例对象
     */
    ExamQuestionInfo update(ExamQuestionInfo examQuestionInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
