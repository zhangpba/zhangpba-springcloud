package com.study.city.exam.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author zhangpba
 * @description 考试试题详情
 * @date 2023/1/15
 */
@Setter
@Getter
@ApiModel
public class ExamPaperUserSubmitResponse {

    @ApiModelProperty(value = "考生考卷表ID")
    private String examPaperUserId;

    @ApiModelProperty(value = "成绩")
    private Integer score;

    @ApiModelProperty(value = "分数线")
    private BigDecimal scoreLine;

    @ApiModelProperty(value = "描述")
    private String message;

    @ApiModelProperty(value = "考生姓名")
    private String username;
}
