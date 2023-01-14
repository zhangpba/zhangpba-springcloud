package com.study.city.exam.service;

import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;

/**
 * (ExamPaperUser)表服务接口
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
public interface ExamPaperUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExamPaperUser queryById(Integer id);

    /**
     * 分页查询
     *
     * @param examPaperUserListRequest 筛选条件
     * @return 查询结果
     */
    PageInfo<ExamPaperUser> queryByPage(ExamPaperUserListRequest examPaperUserListRequest);

    /**
     * 新增数据
     *
     * @param examPaperUser 实例对象
     * @return 实例对象
     */
    ExamPaperUser insert(ExamPaperUser examPaperUser);

    /**
     * 修改数据
     *
     * @param examPaperUser 实例对象
     * @return 实例对象
     */
    ExamPaperUser update(ExamPaperUser examPaperUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
