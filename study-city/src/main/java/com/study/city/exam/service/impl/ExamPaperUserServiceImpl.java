package com.study.city.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamPaper;
import com.study.city.exam.entity.ExamPaperDetail;
import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.ExamQuestionInfo;
import com.study.city.exam.entity.ExamQuestionOptions;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;
import com.study.city.exam.entity.response.ExamPaperDetailResponse;
import com.study.city.exam.entity.response.ExamPaperUserResponse;
import com.study.city.exam.mapper.ExamPaperMapper;
import com.study.city.exam.mapper.ExamPaperUserMapper;
import com.study.city.exam.mapper.ExamQuestionInfoMapper;
import com.study.city.exam.mapper.ExamQuestionOptionsMapper;
import com.study.city.exam.service.ExamPaperDetailService;
import com.study.city.exam.service.ExamPaperUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private ExamPaperUserMapper examPaperUserMapper;

    @Resource
    private ExamPaperDetailService examPaperDetailService;

    @Resource
    private ExamQuestionOptionsMapper examQuestionOptionsMapper;

    @Resource
    private ExamQuestionInfoMapper examQuestionInfoMapper;

    @Resource
    private ExamPaperMapper examPaperMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamPaperUser queryById(Integer id) {
        return this.examPaperUserMapper.queryById(id);
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
        List<ExamPaperUser> examPaperUsers = this.examPaperUserMapper.queryAll(examPaperUserListRequest);
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
        this.examPaperUserMapper.insert(examPaperUser);
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
        this.examPaperUserMapper.update(examPaperUser);
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
        return this.examPaperUserMapper.deleteById(id) > 0;
    }

    @Override
    public ExamPaperUserResponse queryPaperUserDetail(Integer paperUserId) {
        // 考试卷面
        ExamPaperUser examPaperUser = examPaperUserMapper.queryById(paperUserId);
        ExamPaper examPaper = examPaperMapper.queryById(examPaperUser.getExamPaperId());
        ExamPaperUserResponse examPaperUserResponse = new ExamPaperUserResponse();
        BeanUtils.copyProperties(examPaper, examPaperUserResponse);
        examPaperUserResponse.setUsername(examPaperUser.getUserName());

        // 考题详情
        ExamPaperDetail examPaperDetail = new ExamPaperDetail();
        examPaperDetail.setExamPaperUserId(examPaperUser.getId() + "");
        List<ExamPaperDetail> examPaperDetails = examPaperDetailService.queryAll(examPaperDetail);
        List<ExamPaperDetailResponse> paperDetailResponseList = new ArrayList<>();
        for (ExamPaperDetail paperDetail : examPaperDetails) {
            ExamPaperDetailResponse examPaperDetailResponse = new ExamPaperDetailResponse();
            BeanUtils.copyProperties(paperDetail, examPaperDetailResponse);

            ExamQuestionInfo examQuestionInfo = examQuestionInfoMapper.queryById(paperDetail.getId());
            examPaperDetailResponse.setQuestion(examQuestionInfo.getQuestion());
            examPaperDetailResponse.setImageUrl(examQuestionInfo.getImageUrl());
            // 选项列表
            ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
            examQuestionOptions.setQuestionId(paperDetail.getQuestionId());
            List<ExamQuestionOptions> examQuestionOptionsList = examQuestionOptionsMapper.queryExamQuestionOptions(examQuestionOptions);
            examPaperDetailResponse.setOptionsList(examQuestionOptionsList);
            paperDetailResponseList.add(examPaperDetailResponse);
        }
        examPaperUserResponse.setExamPaperDetailList(paperDetailResponseList);
        return examPaperUserResponse;
    }
}
