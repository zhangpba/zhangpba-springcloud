package com.study.city.data.entity.gold;

import com.study.city.data.entity.BaseEntity;
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
public class GoldBase extends BaseEntity {
    // 开盘价
    @ApiModelProperty(name = "开盘价")
    private BigDecimal openingprice;
    // 最高价
    @ApiModelProperty(name = "最高价")
    private BigDecimal maxprice;
    // 最低价
    @ApiModelProperty(name = "最低价")
    private BigDecimal minprice;
    // 更新时间
    @ApiModelProperty(name = "更新时间")
    private Date updatetime;
    // 当天时间
    @ApiModelProperty(name = "当天时间")
    private String date;
    // 品种代号
    @ApiModelProperty(name = "交易机构类型")
    private String exchangeType;
}
