package com.study.city.exam.service;

import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamPaper;
import com.study.city.exam.entity.request.ExamPaperListRequest;

/**
 * (ExamPaper)表服务接口
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:20
 */
public interface IExamPaperService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamPaper queryById(Integer id);

    /**
     * 分页查询
     *
     * @param examPaperRequest 筛选条件
     * @return 查询结果
     */
    PageInfo<ExamPaper> queryByPage(ExamPaperListRequest examPaperRequest);

    /**
     * 新增数据
     *
     * @param examPaper 实例对象
     * @return 实例对象
     */
    ExamPaper insert(ExamPaper examPaper);

    /**
     * 修改数据
     *
     * @param examPaper 实例对象
     * @return 实例对象
     */
    ExamPaper update(ExamPaper examPaper);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
