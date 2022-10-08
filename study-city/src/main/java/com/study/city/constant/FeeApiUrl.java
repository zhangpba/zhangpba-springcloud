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
     * 朋友圈文案(天行数据）http://api.tianapi.com/gjmj/index?key=自己的key
     */
    public static final String TIANXING_PYQWENAN_URL = "http://api.tianapi.com/pyqwenan/index?key=%s" ;

    /**
     * 古籍名句(天行数据）http://api.tianapi.com/gjmj/index?key=自己的key
     */
    public static final String TIANXING_GDMJ_URL = "http://api.tianapi.com/gjmj/index?key=%s" ;

    /**
     * 土味情话(天行数据）http://api.tianapi.com/saylove/index?key=自己的key
     */
    public static final String TIANXING_SAYLOVE_URL = "http://api.tianapi.com/saylove/index?key=%s" ;

    /**
     * 老黄历(天行数据）http://api.tianapi.com/lunar/index?key=自己的key&date=2022-05-13
     */
    public static final String TIANXING_LUNAR_URL = "http://api.tianapi.com/lunar/index?key=%s&date=%s" ;

    /**
     * 每日一小句(天行数据）http://api.tianapi.com/one/index?key=自己的key&date=2022-05-15
     */
    public static final String TIANXING_ONE_URL = "http://api.tianapi.com/one/index?key=%s&date=%s" ;

    /**
     * 网络取名(天行数据) yangrqb http://api.tianapi.com/cname/index?key=APIKEY
     */
    public static final String TIANXING_QNAME_URL ="http://api.tianapi.com/cname/index?key=%s&num=50";
    // yangrqb的key
    public static final String TIANXING_QNAME_APP_KEY_YANGRQB ="0ac5d0cef1b492df43ce53561b73b9a1";


    /**
     * 天气预报API（天行数据）
     *
     * 该接口免费会员每天赠送50次调用额度: http://apis.juhe.cn/simpleWeather/query?city=西安&key=自己的appkey
     *
     * 此API
     *      来自聚合数据：https://dashboard.juhe.cn/data/index/my
     *      使用需要注册，并生成自己的appkey,需要实名制
     *      详细的链接是：https://www.juhe.cn/docs/api/id/73
     */
    public static final String JUHE_WEATHER_URL = "https://apis.juhe.cn/simpleWeather/query?city=%s&key=%s" ;
    // 接口请求Key
    public static final String JUHE_WEATHER_API_KEY = "924fe3e5b94bff5d43c0e55cd2f99667";
}
