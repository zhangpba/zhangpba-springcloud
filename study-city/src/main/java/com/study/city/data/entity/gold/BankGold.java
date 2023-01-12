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
public class BankGold extends GoldBase {
    // 昨收盘价
    @ApiModelProperty(name = "昨收盘价")
    private BigDecimal lastclosingprice;
    // 买入价
    @ApiModelProperty(name = "买入价")
    private BigDecimal buyprice;
    // 买出价
    @ApiModelProperty(name = "买出价")
    private BigDecimal sellprice;
    // 涨跌量
    @ApiModelProperty(name = "涨跌量")
    private BigDecimal changequantity;
    // 中间价
    @ApiModelProperty(name = "中间价")
    private BigDecimal midprice;
}
