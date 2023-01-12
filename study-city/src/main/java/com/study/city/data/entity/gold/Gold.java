package com.study.city.data.entity.gold;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 黄金数据模型-跟数据库交互的
 *
 * @author zhangpab
 * @date 2022-04-16
 */
@Getter
@Setter
@ApiModel(description = "黄金数据")
public class Gold extends GoldBase {
    // 品种代号
    @ApiModelProperty(name = "品种代号")
    private String type;
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
    // 品种名称
    @ApiModelProperty(name = "品种名称")
    private String typename;
    // 最新价
    @ApiModelProperty(name = "最新价")
    private BigDecimal price;
    // 涨跌幅
    @ApiModelProperty(name = "涨跌幅")
    private BigDecimal changepercent;
    // 总成交量
    @ApiModelProperty(name = "总成交量")
    private BigDecimal tradeamount;
    // 成交价
    @ApiModelProperty(name = "成交价")
    private BigDecimal finalprice;
    // 收市价
    @ApiModelProperty(name = "收市价")
    private BigDecimal closingprice;
    // 振幅
    @ApiModelProperty(name = "振幅")
    private BigDecimal amplitude;
}
