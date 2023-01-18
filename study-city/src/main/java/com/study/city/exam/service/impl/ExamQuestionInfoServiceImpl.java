package com.study.city.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.ExamQuestionInfo;
import com.study.city.exam.entity.request.ExamQuestionInfoRequest;
import com.study.city.exam.entity.ExamQuestionOptions;
import com.study.city.exam.entity.QuestionInfo;
import com.study.city.exam.mapper.ExamQuestionInfoMapper;
import com.study.city.exam.mapper.ExamQuestionOptionsMapper;
import com.study.city.exam.mapper.QuestionInfoMapper;
import com.study.city.exam.service.IExamQuestionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (ExamQuestionInfo)表服务实现类
 *
 * @author zhangpba
 * @since 2023-01-13 16:57:31
 */
@Service("examQuestionInfoService")
public class ExamQuestionInfoServiceImpl implements IExamQuestionInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ExamQuestionInfoServiceImpl.class);

    // 小车科目一
    public static final String CAR_ONE = "car_one";
    // 小车科目四
    public static final String CAR_FOUR = "car_four";
    // 客车科目一
    public static final String BUS_ONE = "bus_one";
    // 客车科目四
    public static final String BUS_FOUR = "bus_four";
    // 货车科目一
    public static final String TRACK_ONE = "track_one";
    // 货车科目四
    public static final String TRACK_FOUR = "track_four";
    // 摩托车科目一
    public static final String MOTORCYCLE_ONE = "motorcycle_one";
    // 摩托车科目四
    public static final String MOTORCYCLE_FOUR = "motorcycle_four";

    @Resource
    private ExamQuestionInfoMapper examQuestionInfoMapper;

    @Resource
    private ExamQuestionOptionsMapper examQuestionOptionsMapper;

    @Resource
    private QuestionInfoMapper questionInfoMapper;


    @Override
    @Transactional
    public void synQuestionInfo(Integer id) {
        // 查询数据
        QuestionInfo questionInfo = questionInfoMapper.queryById(id);

        ExamQuestionInfoRequest examQuestionInfoRequest = new ExamQuestionInfoRequest();
        examQuestionInfoRequest.setSourceId(questionInfo.getSourceId() + "");
        List<ExamQuestionInfo> examQuestionInfos = examQuestionInfoMapper.queryAll(examQuestionInfoRequest);
        if (examQuestionInfos == null && examQuestionInfos.isEmpty()) {
            logger.info("ID为{}的题目已经同步过了，无需再次同步！", id);
            return;
        }

        if (questionInfo != null) {
            // 题目数据
            ExamQuestionInfo examQuestionInfo = new ExamQuestionInfo();
            if ("0".equals(questionInfo.getLicenseType())) {
                // 小车 car_one
                if ("科目一".equals(questionInfo.getSubjectType())) {
                    // 科目一
                    examQuestionInfo.setExamType(CAR_ONE);
                }
                if ("科目四".equals(questionInfo.getSubjectType()))
                    // 科目四
                    examQuestionInfo.setExamType(CAR_FOUR);
            } else if ("1".equals(questionInfo.getLicenseType())) {
                // 客车 bus_one
                if ("科目一".equals(questionInfo.getSubjectType())) {
                    // 科目一
                    examQuestionInfo.setExamType(BUS_ONE);
                }
                if ("科目四".equals(questionInfo.getSubjectType())) {
                    // 科目四
                    examQuestionInfo.setExamType(BUS_FOUR);
                }
            } else if ("2".equals(questionInfo.getLicenseType())) {
                // 货车 track_one
                if ("科目一".equals(questionInfo.getSubjectType())) {
                    // 科目一
                    examQuestionInfo.setExamType(TRACK_ONE);
                }
                if ("科目四".equals(questionInfo.getSubjectType())) {
                    // 科目四
                    examQuestionInfo.setExamType(TRACK_FOUR);
                }

            } else if ("3".equals(questionInfo.getLicenseType())) {
                // 摩托车 motorcycle_one
                if ("科目一".equals(questionInfo.getSubjectType())) {
                    // 科目一
                    examQuestionInfo.setExamType(MOTORCYCLE_ONE);
                }
                if ("科目四".equals(questionInfo.getSubjectType())) {
                    // 科目四
                    examQuestionInfo.setExamType(MOTORCYCLE_FOUR);
                }
            }
            examQuestionInfo.setQuestion(questionInfo.getQuestion());
            examQuestionInfo.setExplain(questionInfo.getExplains());
            examQuestionInfo.setType(questionInfo.getQuestionType());
            examQuestionInfo.setImageUrl(questionInfo.getImageUrl());
            examQuestionInfo.setSourceId(questionInfo.getSourceId() + "");
            examQuestionInfo.setAnswer(questionInfo.getKey());
            examQuestionInfoMapper.insert(examQuestionInfo);

            // 选项数据
            String key = questionInfo.getKey();
            List<ExamQuestionOptions> examQuestionOptionsList = new ArrayList<>();
            if (questionInfo.getOptionA() != null && !"".equals(questionInfo.getOptionA())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("A");
                examQuestionOptions.setOption(questionInfo.getOptionA());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (questionInfo.getOptionB() != null && !"".equals(questionInfo.getOptionB())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("B");
                examQuestionOptions.setOption(questionInfo.getOptionB());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (questionInfo.getOptionC() != null && !"".equals(questionInfo.getOptionC())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("C");
                examQuestionOptions.setOption(questionInfo.getOptionC());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (questionInfo.getOptionD() != null && !"".equals(questionInfo.getOptionD())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("D");
                examQuestionOptions.setOption(questionInfo.getOptionD());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (questionInfo.getOptionE() != null && !"".equals(questionInfo.getOptionE())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("E");
                examQuestionOptions.setOption(questionInfo.getOptionE());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (questionInfo.getOptionF() != null && !"".equals(questionInfo.getOptionF())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("F");
                examQuestionOptions.setOption(questionInfo.getOptionF());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (questionInfo.getOptionG() != null && !"".equals(questionInfo.getOptionG())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("G");
                examQuestionOptions.setOption(questionInfo.getOptionG());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (questionInfo.getOptionH() != null && !"".equals(questionInfo.getOptionH())) {
                ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
                examQuestionOptions.setQuestionId(examQuestionInfo.getId());
                examQuestionOptions.setKey("H");
                examQuestionOptions.setOption(questionInfo.getOptionH());
                examQuestionOptionsList.add(examQuestionOptions);
            }
            if (examQuestionOptionsList != null && !examQuestionOptionsList.isEmpty()) {
                examQuestionOptionsMapper.insertBatch(examQuestionOptionsList);
            }

        }
    }

    @Override
    public void synQuestionInfoAll() {
        List<QuestionInfo> questionInfoList = questionInfoMapper.getQuestions();
        if (questionInfoList != null && !questionInfoList.isEmpty()) {
            for (QuestionInfo questionInfo : questionInfoList) {
                this.synQuestionInfo(questionInfo.getId());
            }
        }
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExamQuestionInfo queryById(Integer id) {
        return this.examQuestionInfoMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param examQuestionInfoRequest 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo<ExamQuestionInfo> queryByPage(ExamQuestionInfoRequest examQuestionInfoRequest) {
        PageHelper.startPage(examQuestionInfoRequest.getPageNum(), examQuestionInfoRequest.getPageSize());
        List<ExamQuestionInfo> questions = this.examQuestionInfoMapper.queryAll(examQuestionInfoRequest);
        PageInfo<ExamQuestionInfo> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param examQuestionInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ExamQuestionInfo insert(ExamQuestionInfo examQuestionInfo) {
        this.examQuestionInfoMapper.insert(examQuestionInfo);
        return examQuestionInfo;
    }

    /**
     * 修改数据
     *
     * @param examQuestionInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ExamQuestionInfo update(ExamQuestionInfo examQuestionInfo) {
        this.examQuestionInfoMapper.update(examQuestionInfo);
        return this.queryById(examQuestionInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.examQuestionInfoMapper.deleteById(id) > 0;
    }
}
