package com.study.city.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.QuestionInfo;
import com.study.city.exam.entity.request.QuestionInfoRequest;
import com.study.city.exam.mapper.QuestionInfoMapper;
import com.study.city.exam.service.IQuestionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (QuestionInfo)表服务实现类
 *
 * @author zhanpba
 * @since 2023-01-11 15:12:56
 */
@Service
public class QuestionInfoServiceImpl implements IQuestionInfoService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionInfoServiceImpl.class);

    @Autowired
    private QuestionInfoMapper questionInfoDao;

    @Override
    public PageInfo getQuestions(QuestionInfoRequest questionInfo) {
        PageHelper.startPage(questionInfo.getPageNum(), questionInfo.getPageSize());
        List<QuestionInfo> questions = questionInfoDao.getQuestions();
        PageInfo<QuestionInfo> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }
}

