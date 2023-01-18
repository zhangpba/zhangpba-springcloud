package com.study.city.exam.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (ExamPaper)实体类
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:20
 */
@Setter
@Getter
@ApiModel
public class ExamPaper implements Serializable {
    private static final long serialVersionUID = -34282073825447023L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "试卷名称")
    private String paperName;

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

    @ApiModelProperty(value = "考试开始时间")
    private Date startTime;

    @ApiModelProperty(value = "考试结束时间")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @Override
    public String toString() {
        return "ExamPaper{" +
                "id=" + id +
                ", paperName='" + paperName + '\'' +
                ", examType='" + examType + '\'' +
                ", scoreLine=" + scoreLine +
                ", score=" + score +
                ", totalNum=" + totalNum +
                ", count=" + count +
                ", choiceSingleNum=" + choiceSingleNum +
                ", choiceSingleScore=" + choiceSingleScore +
                ", choiceManyNum=" + choiceManyNum +
                ", choiceManyScore=" + choiceManyScore +
                ", judgeNum=" + judgeNum +
                ", judgeScore=" + judgeScore +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}

