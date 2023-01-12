package com.study.city.exam.service;

import com.github.pagehelper.PageInfo;
import com.study.city.exam.entity.QuestionInfoRequest;

public interface IQuestionInfoService {

    PageInfo getQuestions(QuestionInfoRequest questionInfo);
}