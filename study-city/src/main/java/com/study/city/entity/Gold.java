package com.study.city.entity;

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
public class Gold extends BaseEntity {
    private String type;// "Au(T+D)";
    private String typename;//"黄金延期",
    private BigDecimal price;// "404.01",
    private BigDecimal openingprice;//  "403.11",
    private BigDecimal maxprice;//  "404.90",
    private BigDecimal minprice;// "403.11",
    private BigDecimal changepercent;//  "0.06",
    private BigDecimal lastclosingprice;// "404.11",
    private BigDecimal tradeamount;// "1374.00",
    private Date updatetime;//  "2022-04-16 20:05:00"

    private String date;// 当天
}
