package com.study.city.service;

import com.github.pagehelper.PageInfo;
import com.study.city.entity.question.QuestionInfoRequest;

public interface IQuestionInfoService {

    PageInfo getQuestions(QuestionInfoRequest questionInfo);
}