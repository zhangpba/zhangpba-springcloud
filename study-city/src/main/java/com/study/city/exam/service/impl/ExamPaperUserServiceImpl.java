package com.study.city.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;
import com.study.city.exam.mapper.ExamPaperUserMapper;
import com.study.city.exam.service.ExamPaperUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ExamPaperUser)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
@Service("examPaperUserService")
public class ExamPaperUserServiceImpl implements ExamPaperUserService {
    @Resource
    private ExamPaperUserMapper examPaperUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamPaperUser queryById(Integer id) {
        return this.examPaperUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param examPaperUserListRequest 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo<ExamPaperUser> queryByPage(ExamPaperUserListRequest examPaperUserListRequest) {
        PageHelper.startPage(examPaperUserListRequest.getPageNum(), examPaperUserListRequest.getPageSize());
        List<ExamPaperUser> examPaperUsers = this.examPaperUserDao.queryAll(examPaperUserListRequest);
        PageInfo<ExamPaperUser> pageInfo = new PageInfo<>(examPaperUsers);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param examPaperUser 实例对象
     * @return 实例对象
     */
    @Override
    public ExamPaperUser insert(ExamPaperUser examPaperUser) {
        this.examPaperUserDao.insert(examPaperUser);
        return examPaperUser;
    }

    /**
     * 修改数据
     *
     * @param examPaperUser 实例对象
     * @return 实例对象
     */
    @Override
    public ExamPaperUser update(ExamPaperUser examPaperUser) {
        this.examPaperUserDao.update(examPaperUser);
        return this.queryById(examPaperUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.examPaperUserDao.deleteById(id) > 0;
    }
}
