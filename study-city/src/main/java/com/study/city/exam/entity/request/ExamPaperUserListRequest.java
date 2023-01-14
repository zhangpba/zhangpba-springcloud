package com.study.city.exam.entity.request;

import com.study.city.base.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (ExamPaperUser)实体类
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
@Setter
@Getter
@ApiModel
public class ExamPaperUserListRequest extends Page implements Serializable {
    private static final long serialVersionUID = 911745969199173963L;

    /**
     * 考生ID
     */
    @ApiModelProperty(value = "考生ID")
    private Integer userId;
    /**
     * 考生姓名
     */
    @ApiModelProperty(value = "考生姓名")
    private String userName;
    /**
     * 考卷ID
     */
    @ApiModelProperty(value = "考卷ID")
    private Integer examPaperId;
    /**
     * 考试成绩
     */
    @ApiModelProperty(value = "考试成绩")
    private BigDecimal score;
    /**
     * 考试次数
     */
    @ApiModelProperty(value = "考试次数")
    private BigDecimal batch;
    /**
     * 考试状态 0-未参加，1-已通过，2-未通过
     */
    @ApiModelProperty(value = "考试状态 0-未参加，1-已通过，2-未通过")
    private Integer status;
}

