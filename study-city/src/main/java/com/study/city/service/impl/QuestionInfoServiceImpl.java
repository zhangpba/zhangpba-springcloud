package com.study.city.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.city.entity.question.QuestionInfoRequest;
import com.study.city.mapper.QuestionInfoDao;
import com.study.city.service.IQuestionInfoService;
import com.study.city.entity.question.QuestionInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (QuestionInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-01-11 15:12:56
 */
@Service
public class QuestionInfoServiceImpl implements IQuestionInfoService {

    @Autowired
    private QuestionInfoDao questionInfoDao;

    @Override
    public PageInfo getQuestions(QuestionInfoRequest questionInfo) {
        PageHelper.startPage(questionInfo.getPageNum(), questionInfo.getPageSize());
        List<QuestionInfoDto> questions = questionInfoDao.getQuestions();
        PageInfo<QuestionInfoDto> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }
}

