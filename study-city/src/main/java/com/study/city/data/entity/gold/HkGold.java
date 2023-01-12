package com.study.city.data.entity.gold;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 黄金数据模型
 *
 * @author zhangpab
 * @date 2022-04-16
 */
@Getter
@Setter
@ApiModel(description = "黄金数据")
public class HkGold extends GoldBase {
    // 品种代号
    @ApiModelProperty(name = "品种代号")
    private String type;
    // 买入价
    @ApiModelProperty(name = "买入价")
    private BigDecimal buyprice;
    // 买出价
    @ApiModelProperty(name = "买出价")
    private BigDecimal sellprice;
    // 成交价
    @ApiModelProperty(name = "成交价")
    private BigDecimal finalprice;
    // 收市价
    @ApiModelProperty(name = "收市价")
    private BigDecimal closingprice;
}
