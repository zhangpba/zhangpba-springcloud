package com.study.city.data.entity.gold;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 伦敦黄金数据模型
 *
 * @author zhangpab
 * @date 2022-04-16
 */
@Getter
@Setter
@ApiModel(description = "黄金数据")
public class LdGold extends GoldBase {
    // 品种代号
    @ApiModelProperty(name = "品种代号")
    private String type;
    // 最新价
    @ApiModelProperty(name = "最新价")
    private BigDecimal price;
    // 涨跌幅
    @ApiModelProperty(name = "涨跌幅")
    private BigDecimal changepercent;
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
    // 振幅
    @ApiModelProperty(name = "振幅")
    private BigDecimal amplitude;
}
