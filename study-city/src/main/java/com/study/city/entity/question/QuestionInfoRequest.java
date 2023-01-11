package com.study.city.entity.question;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * (QuestionInfo)表实体类
 *
 * @author makejava
 * @since 2023-01-11 15:12:56
 */
@Setter
@Getter
@ApiModel
public class QuestionInfoRequest {

    @ApiModelProperty(value = "驾照类型，0：小车（C1、C2），1：客车（A1、A3、B1），2：货车（A2、B2），3：摩托车（D、E、F）")
    private String licenseType;
    @ApiModelProperty(value = "科目类型，0：科目一，1：科目四（文明考试）")
    private String subjectType;
    @ApiModelProperty(value = "题目类型（1：判断题，2：单选题，3：多选题）")
    private String questionType;
    @ApiModelProperty(value = "页码")
    int pageNum;
    @ApiModelProperty(value = "页面大小")
    int pageSize;
    @ApiModelProperty(value = "总数")
    int total;
}

