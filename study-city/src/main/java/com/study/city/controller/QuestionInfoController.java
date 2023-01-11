package com.study.city.controller;


import com.github.pagehelper.PageInfo;
import com.study.city.entity.question.QuestionInfoRequest;
import com.study.city.service.IQuestionInfoService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Dictionary;

/**
 * (QuestionInfo)表控制层
 *
 * @author makejava
 * @since 2023-01-11 15:12:53
 */
@RestController
@RequestMapping("questionInfo")
@Api(value = "考试题库", tags = "考试题库")
public class QuestionInfoController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionInfoController.class);

    /**
     * 服务对象
     */
    @Resource
    private IQuestionInfoService questionInfoService;


    /**
     * 配置信息
     *
     * @return
     */
    @PostMapping(value = "/get-questions")
    @ApiOperation(value = "查看题目", response = Dictionary.class)
    public ResponseMessage getQuestions(@RequestBody QuestionInfoRequest questionInfo) {
        logger.info("进入方法 getQuestions： 参数： {} ,{}", questionInfo.getPageNum(), questionInfo.getPageSize());
        PageInfo pageInfo = questionInfoService.getQuestions(questionInfo);
        return ResponseMessage.success(pageInfo);
    }
}

