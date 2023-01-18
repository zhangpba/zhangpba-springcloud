package com.study.city.exam.service.impl;

import com.study.city.config.exception.CustomException;
import com.study.city.exam.entity.ExamPaper;
import com.study.city.exam.entity.ExamPaperDetail;
import com.study.city.exam.entity.ExamPaperUser;
import com.study.city.exam.entity.ExamQuestionInfo;
import com.study.city.exam.entity.request.ExamPaperUserListRequest;
import com.study.city.exam.entity.request.ExamQuestionInfoRequest;
import com.study.city.exam.mapper.ExamPaperDetailMapper;
import com.study.city.exam.mapper.ExamPaperMapper;
import com.study.city.exam.mapper.ExamPaperUserMapper;
import com.study.city.exam.mapper.ExamQuestionInfoMapper;
import com.study.city.exam.service.ExamPaperDetailService;
import com.study.city.user.entity.SysUser;
import com.study.city.user.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * (ExamPaperDetail)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:46
 */
@Service("examPaperDetailService")
public class ExamPaperDetailServiceImpl implements ExamPaperDetailService {

    private static final Logger logger = LoggerFactory.getLogger(ExamPaperDetailServiceImpl.class);

    @Resource
    private ExamPaperMapper examPaperMapper;

    @Resource
    private ExamPaperDetailMapper examPaperDetailMapper;

    @Resource
    private ExamPaperUserMapper examPaperUserMapper;

    @Resource
    private ExamPaperDetailMapper paperDetailMapper;

    @Resource
    private ExamQuestionInfoMapper examQuestionInfoMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamPaperDetail queryById(Integer id) {
        return this.paperDetailMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param examPaperDetail 筛选条件
     * @return 查询结果
     */
    @Override
    public List<ExamPaperDetail> queryAll(ExamPaperDetail examPaperDetail) {
        return this.paperDetailMapper.queryAll(examPaperDetail);
    }

    /**
     * 新增数据
     *
     * @param examPaperDetail 实例对象
     * @return 实例对象
     */
    @Override
    public ExamPaperDetail insert(ExamPaperDetail examPaperDetail) {
        this.paperDetailMapper.insert(examPaperDetail);
        return examPaperDetail;
    }

    /**
     * 修改数据
     *
     * @param examPaperDetail 实例对象
     * @return 实例对象
     */
    @Override
    public ExamPaperDetail update(ExamPaperDetail examPaperDetail) {
        this.paperDetailMapper.update(examPaperDetail);
        return this.queryById(examPaperDetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.paperDetailMapper.deleteById(id) > 0;
    }

    /**
     * 生成考生考卷明细
     *
     * @param userId      考生ID
     * @param examPaperId 考试定义ID
     */
    @Override
    @Transactional
    public String buildExamPaperUserDetail(Integer userId, Integer examPaperId) {
        // 用户表
        SysUser user = sysUserMapper.queryById(userId);
        if (user == null) {
            throw new CustomException(608, "用户信息为空！");
        }
        // 考卷定义信息
        ExamPaper examPaper = examPaperMapper.queryById(examPaperId);
        if (user == null) {
            throw new CustomException(609, "考卷定义信息为空! examPaperId：" + examPaperId);
        }
        String message = "";
        // 查询该考生是否已经考过该考试
        ExamPaperUserListRequest examPaperUserListRequest = new ExamPaperUserListRequest();
        examPaperUserListRequest.setUserId(userId);
        examPaperUserListRequest.setExamPaperId(examPaperId);
        List<ExamPaperUser> examPaperUsers = examPaperUserMapper.queryAll(examPaperUserListRequest);
        if (!CollectionUtils.isEmpty(examPaperUsers)) {
            if (examPaperUsers.size() >= examPaper.getCount()) {
                throw new CustomException(607, "您已超过考试次数上限制！");
            } else {
                // 获取最大的批次号
                BigDecimal maxBatceh = examPaperUsers.stream().max(Comparator.comparing(ExamPaperUser::getBatch)).get().getBatch();
                // 生成考卷
                message = this.getExamPaperDetails(user, maxBatceh, examPaper);
            }
        } else {
            message = this.getExamPaperDetails(user, BigDecimal.ONE, examPaper);
        }
        return message;
    }


    /**
     * 生成考卷
     *
     * @param user      考生信息
     * @param maxBatceh 最大的批次号
     * @param examPaper 试卷定义信息
     * @return 试卷ID
     */
    private String getExamPaperDetails(SysUser user, BigDecimal maxBatceh, ExamPaper examPaper) {
        // 一、生成考生考卷
        ExamPaperUser examPaperUser = new ExamPaperUser();
        examPaperUser.setUserId(user.getUserId());
        examPaperUser.setUserName(user.getUsername());
        examPaperUser.setBatch(maxBatceh);
        examPaperUser.setStatus(0); // 0-未参加，1-已通过，2-未通过
        examPaperUser.setExamPaperId(examPaper.getId());
        examPaperUser.getCreateTime(new Date());
        examPaperUserMapper.insert(examPaperUser);

        // 二、从题库中获取考试试题
        List<ExamQuestionInfo> examQuestionInfos = this.getExamQuestionInfos(user, examPaper);

        // 三、生成本次考试明细
        List<ExamPaperDetail> examPaperDetails = new ArrayList<>();
        for (ExamQuestionInfo e : examQuestionInfos) {
            ExamPaperDetail examPaperDetail = new ExamPaperDetail();
            examPaperDetail.setQuestionId(e.getId());
            examPaperDetail.setExamPaperUserId(examPaperUser.getId() + "");
            examPaperDetail.setRight(e.getAnswer());
            if ("1".equals(e.getType())) {
                examPaperDetail.setPoints(examPaper.getJudgeScore().intValue() + "");
            }
            if ("2".equals(e.getType())) {
                examPaperDetail.setPoints(examPaper.getChoiceSingleScore().intValue() + "");
            }
            if ("3".equals(e.getType())) {
                examPaperDetail.setPoints(examPaper.getChoiceManyScore().intValue() + "");
            }
            examPaperDetail.setCreateTime(new Date());
            examPaperDetails.add(examPaperDetail);
        }
        examPaperDetailMapper.insertBatch(examPaperDetails);
        return examPaperUser.getId() + "";
    }


    /**
     * 获取考试试题
     *
     * @param user      用户信息
     * @param examPaper 考卷定义信息
     * @return
     */
    private List<ExamQuestionInfo> getExamQuestionInfos(SysUser user, ExamPaper examPaper) {
        // 根据考试类型在题库取对应数量的试题
        // 判断题
        ExamQuestionInfoRequest examQuestionInfoRequest = new ExamQuestionInfoRequest();

        BigDecimal judgeNum = examPaper.getJudgeNum();// 判断题数
        BigDecimal choiceSingleNum = examPaper.getChoiceSingleNum(); // 单选题数
        BigDecimal choiceManyNum = examPaper.getChoiceManyNum(); // 多选题数

        examQuestionInfoRequest.setExamType(examPaper.getExamType());

        List<ExamQuestionInfo> questionInfoList = new ArrayList<>();
        if (judgeNum != null && judgeNum.intValue() > 0) {
            examQuestionInfoRequest.setType("1");
            List<ExamQuestionInfo> judgeList = examQuestionInfoMapper.queryAll(examQuestionInfoRequest);
            List<ExamQuestionInfo> judge = judgeList.subList(0, judgeNum.intValue());
            questionInfoList.addAll(judge);
        }

        if (choiceSingleNum != null && choiceSingleNum.intValue() > 0) {
            examQuestionInfoRequest.setType("2");
            List<ExamQuestionInfo> singleList = examQuestionInfoMapper.queryAll(examQuestionInfoRequest);
            List<ExamQuestionInfo> single = singleList.subList(0, choiceSingleNum.intValue());
            questionInfoList.addAll(single);
        }

        if (choiceManyNum != null && choiceManyNum.intValue() > 0) {
            examQuestionInfoRequest.setType("3");
            List<ExamQuestionInfo> manyList = examQuestionInfoMapper.queryAll(examQuestionInfoRequest);
            List<ExamQuestionInfo> many = manyList.subList(0, choiceManyNum.intValue());
            questionInfoList.addAll(many);
        }
        return questionInfoList;
    }
}
