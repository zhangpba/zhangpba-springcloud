package com.data.chain.eventflow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/16
 */
@ApiModel
@Data
public class QueryFlowDto {
    @ApiModelProperty (value ="模板名称")
    private String modelName;
    @ApiModelProperty (value ="业务类型")
    private String businessType;
    @ApiModelProperty(value ="是否有效（0-有效；1-无效）")
    private Integer isValid;
    @ApiModelProperty(value ="分页页数")
    private Integer pageNum;
    @ApiModelProperty(value ="分页页容量")
    private Integer pageSize;
}
