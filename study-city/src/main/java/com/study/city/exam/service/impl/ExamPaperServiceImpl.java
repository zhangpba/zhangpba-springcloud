package com.study.city.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamPaper;
import com.study.city.exam.entity.request.ExamPaperListRequest;
import com.study.city.exam.mapper.ExamPaperMapper;
import com.study.city.exam.service.ExamPaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ExamPaper)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:20
 */
@Service("examPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {

    private static final Logger logger = LoggerFactory.getLogger(ExamPaperServiceImpl.class);

    @Resource
    private ExamPaperMapper examPaperDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamPaper queryById(Integer id) {
        return this.examPaperDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param examPaperRequest 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo<ExamPaper> queryByPage(ExamPaperListRequest examPaperRequest) {
        PageHelper.startPage(examPaperRequest.getPageNum(), examPaperRequest.getPageSize());
        List<ExamPaper> examPapers = this.examPaperDao.queryAll(examPaperRequest);
        PageInfo<ExamPaper> pageInfo = new PageInfo<>(examPapers);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param examPaper 实例对象
     * @return 实例对象
     */
    @Override
    public ExamPaper insert(ExamPaper examPaper) {
        this.examPaperDao.insert(examPaper);
        return examPaper;
    }

    /**
     * 修改数据
     *
     * @param examPaper 实例对象
     * @return 实例对象
     */
    @Override
    public ExamPaper update(ExamPaper examPaper) {
        this.examPaperDao.update(examPaper);
        return this.queryById(examPaper.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.examPaperDao.deleteById(id) > 0;
    }
}
