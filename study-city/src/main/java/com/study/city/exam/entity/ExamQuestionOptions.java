package com.study.city.exam.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * (ExamQuestionOptions)实体类
 *
 * @author zhanpba
 * @since 2023-01-13 17:02:56
 */
@Setter
@Getter
@ApiModel(description = "题目选项")
public class ExamQuestionOptions implements Serializable {
    private static final long serialVersionUID = -88735888080005787L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "选项 (A、B、C、D)")
    private String key;

    @ApiModelProperty(value = "选项内容")
    private String option;

    @ApiModelProperty(value = "题目主键")
    private Integer questionId;

    @Override
    public String toString() {
        return "ExamQuestionOptions{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", option='" + option + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}

