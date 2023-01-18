package com.study.city.exam.entity.request;

import com.study.city.base.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * (ExamQuestionInfo)实体类
 *
 * @author zhanpba
 * @since 2023-01-13 16:57:31
 */
@Setter
@Getter
@ApiModel(description = "题目查询条件")
public class ExamQuestionInfoRequest extends Page implements Serializable {
    private static final long serialVersionUID = 160106184288964272L;

    @ApiModelProperty(value = "题目内容")
    private String question;

    @ApiModelProperty(value = "答案（options_info表中的key）")
    private String answer;

    @ApiModelProperty(value = "最佳解释")
    private String explain;

    @ApiModelProperty(value = "图片")
    private String imageUrl;

    @ApiModelProperty(value = "题目类型（1：判断题，2：单选题，3：多选题）")
    private String type;

    @ApiModelProperty(value = "考试类型（car_one:小车科目一，car_four:小车科目四，bus_one:客车科目一，bus_four:客车科目四，track_one:货车科目一，track_four:货车科目四，motorcycle_one:摩托车科目一，motorcycle_four:摩托车科目四）")
    private String examType;

    @ApiModelProperty(value = "原始题目id")
    private String sourceId;

    @Override
    public String toString() {
        return "ExamQuestionInfoRequest{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", explain='" + explain + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                ", examType='" + examType + '\'' +
                ", sourceId='" + sourceId + '\'' +
                '}';
    }
}

