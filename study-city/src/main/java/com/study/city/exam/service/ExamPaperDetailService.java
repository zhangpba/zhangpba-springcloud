package com.study.city.exam.service;

import com.study.city.exam.entity.ExamPaperDetail;

import java.util.List;

/**
 * (ExamPaperDetail)表服务接口
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:46
 */
public interface ExamPaperDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamPaperDetail queryById(Integer id);

    /**
     * 分页查询
     *
     * @param examPaperDetail 筛选条件
     * @return 查询结果
     */
    List<ExamPaperDetail> queryAll(ExamPaperDetail examPaperDetail);

    /**
     * 新增数据
     *
     * @param examPaperDetail 实例对象
     * @return 实例对象
     */
    ExamPaperDetail insert(ExamPaperDetail examPaperDetail);

    /**
     * 修改数据
     *
     * @param examPaperDetail 实例对象
     * @return 实例对象
     */
    ExamPaperDetail update(ExamPaperDetail examPaperDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 生成考生考卷明细
     *
     * @param userId      考生ID
     * @param ExamPaperId 考试定义ID
     */
    String buildExamPaperUserDetail(Integer userId, Integer ExamPaperId);
}
