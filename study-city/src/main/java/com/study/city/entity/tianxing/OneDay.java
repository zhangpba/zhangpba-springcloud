package com.study.city.entity.tianxing;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpba
 * @description ONE一个每日一小句
 * @date 2022/5/14
 */
@Setter
@Getter
public class OneDay {
    // ONE一个ID
    private Integer oneid;
    // 句子来源
    private String word;
    // 句子
    private String wordfrom;
    // 配图
    private String imgurl;
    // 配图作者
    private String imgauthor;
    // 时间
    private String date;
    // 本地图片存储的位置
    private String imgPath;
}
