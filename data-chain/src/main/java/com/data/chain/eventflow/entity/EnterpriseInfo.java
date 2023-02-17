package com.data.chain.eventflow.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("企业及其监管机构信息")
@TableName("ENTERPRISE_INFO")
public class EnterpriseInfo {
    @ApiModelProperty(value ="企业编码")
    private String enterpriseCode;
    @ApiModelProperty (value ="企业名称")
    private String enterpriseName;
    @ApiModelProperty (value ="企业全称")
    private String fullName;
    @ApiModelProperty (value ="省编码")
    private String province;
    @ApiModelProperty (value ="省名称")
    private String provinceName;
    @ApiModelProperty (value ="执法处编码")
    private String enforcement;
    @ApiModelProperty (value ="执法处名称")
    private String enforcementName;
    @ApiModelProperty (value ="市编码")
    private String city;
    @ApiModelProperty (value ="市名称")
    private String cityName;
    @ApiModelProperty (value ="县编码")
    private String county;
    @ApiModelProperty (value ="县名称")
    private String countyName;
}