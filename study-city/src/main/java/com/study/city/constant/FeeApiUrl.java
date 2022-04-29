package com.study.city.constant;

/**
 * 免费开放接口
 *
 * @author zhangpba
 * @date 2022-04-16
 */
public class FeeApiUrl {

    /**
     * 天气预报数据API：http://wthrcdn.etouch.cn/weather_mini?city=城市名称
     */
    public static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?city=";

    /**
     * 黄金数据API
     *
     * 上海黄金交易所价格：   https://api.jisuapi.com/gold/shgold?appkey=自己的appkey
     * 上海期货交易所价格：   https://api.jisuapi.com/gold/shfutures?appkey=自己的appkey
     * 香港黄金价格：        https://api.jisuapi.com/gold/hkgold?appkey=自己的appkey
     * 银行账户黄金价格:     https://api.jisuapi.com/gold/bank?appkey=自己的appkey
     * 伦敦金，银价格：      https://api.jisuapi.com/gold/london?appkey=自己的appkey
     *
     * 此API
     *      来自进制数据：https://www.binstd.com/my/authentication.php
     *      使用需要注册，并生成自己的appkey，
     *      详细的链接是：https://www.binstd.com/debug/gold/
     */
    public static final String GOLD_URL = "https://api.binstd.com/gold/%s?appkey=";
}
