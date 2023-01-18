package com.study.city.exam.entity.request;


import com.study.city.base.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 请求体
 *
 * @author zhangpba
 * <p>
 * 校验字段：https://blog.csdn.net/hljczm/article/details/109485165
 * @since 2023-01-11 15:12:56
 */
@Setter
@Getter
@ApiModel
public class QuestionInfoRequest extends Page implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "驾照类型，0：小车（C1、C2），1：客车（A1、A3、B1），2：货车（A2、B2），3：摩托车（D、E、F）")
    private String licenseType;

    @ApiModelProperty(value = "科目类型，0：科目一，1：科目四（文明考试）")
    private String subjectType;

    @ApiModelProperty(value = "题目类型（1：判断题，2：单选题，3：多选题）")
    @NotEmpty(message = "题目类型不能为空")
    private String questionType;
}