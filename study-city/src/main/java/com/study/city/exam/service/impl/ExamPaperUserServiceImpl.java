package com.study.city.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.config.exception.CustomException;
import com.study.city.exam.entity.ExamPaper;
import com.study.city.exam.entity.ExamPaperDetail;
import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.ExamQuestionInfo;
import com.study.city.exam.entity.ExamQuestionOptions;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;
import com.study.city.exam.entity.response.ExamPaperDetailResponse;
import com.study.city.exam.entity.response.ExamPaperUserResponse;
import com.study.city.exam.entity.response.ExamPaperUserSubmitResponse;
import com.study.city.exam.mapper.ExamPaperMapper;
import com.study.city.exam.mapper.ExamPaperUserMapper;
import com.study.city.exam.mapper.ExamQuestionInfoMapper;
import com.study.city.exam.mapper.ExamQuestionOptionsMapper;
import com.study.city.exam.service.IExamPaperDetailService;
import com.study.city.exam.service.IExamPaperUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * (ExamPaperUser)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
@Service("examPaperUserService")
public class ExamPaperUserServiceImpl implements IExamPaperUserService {
    private static final Logger logger = LoggerFactory.getLogger(ExamPaperUserServiceImpl.class);

    @Resource
    private ExamPaperUserMapper examPaperUserMapper;

    @Resource
    private IExamPaperDetailService examPaperDetailService;

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
        // 考生考卷基本信息
        ExamPaperUser examPaperUser = examPaperUserMapper.queryById(paperUserId);
        if (examPaperUser == null) {
            throw new CustomException(605, "考生考卷基本信息为空！");
        }
        ExamPaper examPaper = examPaperMapper.queryById(examPaperUser.getExamPaperId());
        if (examPaper == null) {
            throw new CustomException(606, "考卷定义信息为空！");
        }
        ExamPaperUserResponse examPaperUserResponse = new ExamPaperUserResponse();
        BeanUtils.copyProperties(examPaper, examPaperUserResponse);
        examPaperUserResponse.setUsername(examPaperUser.getUserName());

        // 考题详情
        ExamPaperDetail examPaperDetail = new ExamPaperDetail();
        examPaperDetail.setExamPaperUserId(examPaperUser.getId() + "");
        List<ExamPaperDetail> examPaperDetails = examPaperDetailService.queryAll(examPaperDetail);
        List<ExamPaperDetailResponse> paperDetailResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(examPaperDetails)) {
            for (ExamPaperDetail paperDetail : examPaperDetails) {
                // logger.info("明细paperDetail：{}", paperDetail.toString());
                ExamPaperDetailResponse examPaperDetailResponse = new ExamPaperDetailResponse();
                BeanUtils.copyProperties(paperDetail, examPaperDetailResponse);

                ExamQuestionInfo examQuestionInfo = examQuestionInfoMapper.queryById(paperDetail.getQuestionId());
                // logger.info("明细examQuestionInfo：{}", examQuestionInfo.toString());
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
        } else {
            throw new CustomException(604, "考题详情明细为空！");
        }
        return examPaperUserResponse;
    }

    /**
     * 提交考试试卷
     *
     * @param paperUserId 考试试卷ID
     * @return
     */
    @Override
    @Transactional
    public ExamPaperUserSubmitResponse submitExamPaper(Integer paperUserId) {
        ExamPaperUserSubmitResponse examPaperUserSubmitResponse = new ExamPaperUserSubmitResponse();
        String message = null;
        if (paperUserId == null) {
            throw new CustomException(601, "考试试卷ID不能为空！");
        }
        ExamPaperDetail examPaperDetail = new ExamPaperDetail();
        examPaperDetail.setExamPaperUserId(paperUserId + "");
        List<ExamPaperDetail> examPaperDetails = examPaperDetailService.queryAll(examPaperDetail);
        if (examPaperDetails != null && !examPaperDetails.isEmpty()) {
            // 是否答试卷
            boolean isAlerdy = true;
            for (ExamPaperDetail paperDetail : examPaperDetails) {
                if (paperDetail.getAnswer() == null) {
                    isAlerdy = false;
                    break;
                }
            }
            if (!isAlerdy) {
                throw new CustomException(602, "试卷没有答完，请检查试卷！");
            }

            // 计算考试成绩
            Integer score = 0;
            for (ExamPaperDetail paperDetail : examPaperDetails) {
                if (paperDetail.getAnswer().equals(paperDetail.getRight())) {
                    paperDetail.setScore(paperDetail.getPoints());
                    score = score + Integer.parseInt(paperDetail.getPoints());
                }
            }

            // 更新考试试卷上的成绩、批次
            ExamPaperUser examPaperUser = examPaperUserMapper.queryById(paperUserId);
            if (examPaperUser == null) {
                throw new CustomException(611, "考试试卷基础信息不存在！");
            }
            examPaperUser.setScore(BigDecimal.valueOf(score));
            // 查寻及格线
            ExamPaper examPaper = examPaperMapper.queryById(examPaperUser.getExamPaperId());
            if (examPaper == null) {
                throw new CustomException(610, "考试试卷配追信息不能为空！");
            }
            if (examPaper.getScoreLine().compareTo(BigDecimal.valueOf(score)) < 0) {
                // 及格
                message = "及格";
                // 0-未参加，1-已通过，2-未通过
                examPaperUser.setStatus(1);
            } else {
                // 不及格
                message = "不及格";
                examPaperUser.setStatus(2);
            }
            // 更新考试试卷成绩,和批次
            examPaperUserMapper.update(examPaperUser);
            examPaperUserSubmitResponse.setUsername(examPaperUser.getUserName());
            examPaperUserSubmitResponse.setExamPaperUserId(paperUserId + "");
            examPaperUserSubmitResponse.setScore(score);
            examPaperUserSubmitResponse.setScoreLine(examPaper.getScoreLine());
        } else {
            throw new CustomException(603, "考试试卷已经过期！");
        }

        examPaperUserSubmitResponse.setMessage(message);
        return examPaperUserSubmitResponse;
    }
}
