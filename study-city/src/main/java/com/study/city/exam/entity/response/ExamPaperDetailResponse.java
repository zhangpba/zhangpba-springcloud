package com.study.city.exam.entity.response;

import com.study.city.exam.entity.ExamQuestionOptions;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhangpba
 * @description 考试试题详情
 * @date 2023/1/15
 */
@Setter
@Getter
@ApiModel
public class ExamPaperDetailResponse {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 题目id
     */
    private Integer questionId;

    /**
     * 题目内容
     */
    private String question;

    /**
     * 图片
     */
    private String imageUrl;

    /**
     * 考生考卷表ID
     */
    private String examPaperUserId;
    /**
     * 考生答案
     */
    private String answer;
    /**
     * 正确答案
     */
    private String right;
    /**
     * 得分
     */
    private String score;
    /**
     * 本题的分值
     */
    private String points;

    /**
     * 选项
     */
    private List<ExamQuestionOptions> optionsList;
}
