package com.study.city.exam.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * (ExamPaperDetail)考试试题保存草稿请求模型
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:46
 */
@Setter
@Getter
@ApiModel
public class ExamPaperDetailSaveRequest implements Serializable {
    private static final long serialVersionUID = 627503953144307695L;

    @ApiModelProperty(value = "主键")
    @NotEmpty(message = "题目主键不能为空")
    private Integer id;

    @ApiModelProperty(value = "题目id")
    private Integer questionId;

    @ApiModelProperty(value = "考生考卷表ID")
    private String examPaperUserId;

    @ApiModelProperty(value = "考生答案")
    @NotEmpty(message = "考生答案不能为空")
    private String answer;

    @Override
    public String toString() {
        return "ExamPaperDetailSaveRequest{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", examPaperUserId='" + examPaperUserId + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}

