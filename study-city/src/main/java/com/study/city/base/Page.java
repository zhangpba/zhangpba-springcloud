package com.study.city.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author zhangpba
 * @description 分页组件
 * @date 2023/1/14
 */
@Setter
@Getter
@ApiModel(description = "分页组件")
public class Page implements Serializable {

    @ApiModelProperty(value = "分页参数-页码")
    @Min(message = "页码不能小于0", value = 0)
    private int pageNum;

    @ApiModelProperty(value = "分页参数-页面大小")
    @Min(message = "页面不能小于0", value = 0)
    private int pageSize;

    @ApiModelProperty(value = "分页参数-总数")
    private int total;
}
