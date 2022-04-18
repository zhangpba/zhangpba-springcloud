package com.study.city.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 黄金数据模型
 *
 * @author zhangpab
 * @date 2022-04-16
 */
@Getter
@Setter
@ApiModel(description = "黄金数据")
public class Gold extends BaseEntity {
    // 品种代号
    @ApiModelProperty(name = "品种代号")
    private String type;
    // 品种名称
    @ApiModelProperty(name = "品种名称")
    private String typename;
    // 最新价
    @ApiModelProperty(name = "最新价")
    private BigDecimal price;
    // 开盘价
    @ApiModelProperty(name = "开盘价")
    private BigDecimal openingprice;
    // 最高价
    @ApiModelProperty(name = "最高价")
    private BigDecimal maxprice;
    // 最低价
    @ApiModelProperty(name = "最低价")
    private BigDecimal minprice;
    // 涨跌幅
    @ApiModelProperty(name = "涨跌幅")
    private BigDecimal changepercent;
    // 昨收盘价
    @ApiModelProperty(name = "昨收盘价")
    private BigDecimal lastclosingprice;
    // 总成交量
    @ApiModelProperty(name = "总成交量")
    private BigDecimal tradeamount;
    // 更新时间
    @ApiModelProperty(name = "更新时间")
    private Date updatetime;

    // 当天时间
    @ApiModelProperty(name = "当天时间")
    private String date;
}
