package com.study.city.exam.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (ExamPaperDetail)实体类
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:46
 */
@Setter
@Getter
@ApiModel
public class ExamPaperDetail implements Serializable {
    private static final long serialVersionUID = 627503953144307695L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "题目id")
    private Integer questionId;

    @ApiModelProperty(value = "考生考卷表ID")
    private String examPaperUserId;

    @ApiModelProperty(value = "考生答案")
    private String answer;

    @ApiModelProperty(value = "正确答案")
    private String right;

    @ApiModelProperty(value = "得分")
    private String score;

    @ApiModelProperty(value = "本题的分值")
    private String points;

    @ApiModelProperty(value = "上一道题的id")
    private Integer previous;

    @ApiModelProperty(value = "下一道题的id")
    private Integer next;

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
        return "ExamPaperDetail{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", examPaperUserId='" + examPaperUserId + '\'' +
                ", answer='" + answer + '\'' +
                ", right='" + right + '\'' +
                ", score='" + score + '\'' +
                ", points='" + points + '\'' +
                ", previous=" + previous +
                ", next=" + next +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}

