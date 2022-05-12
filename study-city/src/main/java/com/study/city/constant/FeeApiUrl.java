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
     * 黄金数据API（进制数据）
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


    /**
     * 生日性格API（天行数据）
     *
     * 该接口免费会员每天赠送100次调用额度: http://api.tianapi.com/dob/index?key=自己的key&m=1&d=20
     *
     * 此API
     *      来自进制数据：https://www.tianapi.com/list/
     *      使用需要注册，并生成自己的key
     *      详细的链接是：https://www.tianapi.com/apiview/27
     */
    public static final String TIANXING_CHARACTERS_URL = "http://api.tianapi.com/dob/index?key=%s&m=%s&d=%s" ;

    /**
     * 朋友圈文案(天行数据）
     */
    public static final String TIANXING_PYQWENAN_URL = "http://api.tianapi.com/pyqwenan/index?key=%s" ;
}
