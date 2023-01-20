package com.study.city.exam.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpba
 * @description 考试类型
 * @date 2023/1/20
 */
@Setter
@Getter
@ApiModel(description = "考试类型")
public class ExamType {
    // 考试类型code
    private String type;

    // 考试类型m描述
    private String desc;
}
