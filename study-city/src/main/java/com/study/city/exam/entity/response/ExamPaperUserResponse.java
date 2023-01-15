package com.study.city.exam.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangpba
 * @description 考试试题列表
 * @date 2023/1/15
 */
@Setter
@Getter
@ApiModel
public class ExamPaperUserResponse {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "试卷名称")
    private String paperName;

    @ApiModelProperty(value = "考生姓名")
    private String username;

    @ApiModelProperty(value = "考试类型")
    private String examType;

    @ApiModelProperty(value = "及格线")
    private BigDecimal scoreLine;

    @ApiModelProperty(value = "考试成绩")
    private BigDecimal score;

    @ApiModelProperty(value = "考卷总共包含多少个题目")
    private BigDecimal totalNum;

    @ApiModelProperty(value = "可考次数")
    private Integer count;

    @ApiModelProperty(value = "单选题数量")
    private BigDecimal choiceSingleNum;

    @ApiModelProperty(value = "单选题分数")
    private BigDecimal choiceSingleScore;

    @ApiModelProperty(value = "多选题数量")
    private BigDecimal choiceManyNum;

    @ApiModelProperty(value = "多选题分数")
    private BigDecimal choiceManyScore;

    @ApiModelProperty(value = "判断题数量")
    private BigDecimal judgeNum;

    @ApiModelProperty(value = "判断题分数")
    private BigDecimal judgeScore;

    // 试题列表
    @ApiModelProperty(value = "试题列表")
    private List<ExamPaperDetailResponse> examPaperDetailList;
}
