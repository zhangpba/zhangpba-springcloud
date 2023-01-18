package com.study.city.exam.service.impl;

import com.study.city.exam.entity.ExamQuestionOptions;
import com.study.city.exam.mapper.ExamQuestionOptionsMapper;
import com.study.city.exam.service.ExamQuestionOptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ExamQuestionOptions)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-13 17:02:56
 */
@Service("examQuestionOptionsService")
public class ExamQuestionOptionsServiceImpl implements ExamQuestionOptionsService {
    private static final Logger logger = LoggerFactory.getLogger(ExamQuestionOptionsServiceImpl.class);

    @Resource
    private ExamQuestionOptionsMapper examQuestionOptionsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamQuestionOptions queryById(Integer id) {
        return this.examQuestionOptionsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param examQuestionOptions 筛选条件
     * @return 查询结果
     */
    @Override
    public List<ExamQuestionOptions> queryExamQuestionOptions(ExamQuestionOptions examQuestionOptions) {
        return this.examQuestionOptionsDao.queryExamQuestionOptions(examQuestionOptions);
    }

    /**
     * 新增数据
     *
     * @param examQuestionOptions 实例对象
     * @return 实例对象
     */
    @Override
    public ExamQuestionOptions insert(ExamQuestionOptions examQuestionOptions) {
        this.examQuestionOptionsDao.insert(examQuestionOptions);
        return examQuestionOptions;
    }

    /**
     * 修改数据
     *
     * @param examQuestionOptions 实例对象
     * @return 实例对象
     */
    @Override
    public ExamQuestionOptions update(ExamQuestionOptions examQuestionOptions) {
        this.examQuestionOptionsDao.update(examQuestionOptions);
        return this.queryById(examQuestionOptions.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.examQuestionOptionsDao.deleteById(id) > 0;
    }
}
