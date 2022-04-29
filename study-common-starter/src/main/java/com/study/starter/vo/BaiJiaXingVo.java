package com.study.starter.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 百家姓模型
 *
 * @author zhangpba
 * @date 2022-04-11
 */
@Getter
@Setter
public class BaiJiaXingVo {

    @ApiModelProperty(value = "中文姓")
    private String zhName;

    @ApiModelProperty(value = "读音")
    private String letterName;

    @Override
    public String toString() {
        return "{ zhName='" + zhName + '\'' + ", letterName='" + letterName + '\'' + '}';
    }
}
