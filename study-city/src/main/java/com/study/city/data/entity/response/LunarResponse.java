package com.study.city.data.entity.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhangpba
 * @description 老黄历
 * @date 2023/1/25
 */
@Setter
@Getter
public class LunarResponse implements Serializable {
    // 公历日期
    private String gregoriandate;
    // 农历日期
    private String lunardate;
    // 农历节日
    private String lunar_festival;
    // 公历节日
    private String festival;
    // 适宜
    private String fitness;
    // 不宜
    private String taboo;
    // 神位
    private String shenwei;
    // 胎神
    private String taishen;
    // 冲煞
    private String chongsha;
    // 岁煞
    private String suisha;
    // 五行甲子
    private String wuxingjiazi;
    // 五行年
    private String wuxingnayear;
    // 五行月
    private String wuxingnamonth;
    // 星宿
    private String xingsu;
    // 彭祖
    private String pengzu;
    // 见神
    private String jianshen;
    // 天干地支年
    private String tiangandizhiyear;
    // 天干地支月
    private String tiangandizhimonth;
    // 天干地支日
    private String tiangandizhiday;
    // 季节
    private String lmonthname;
    // 生肖
    private String shengxiao;
    // 农历月
    private String lubarmonth;
    // 农历日
    private String lunarday;
    // 节气
    private String jieqi;
}
