package com.study.city.enums;

/**
 * 黄金枚举类
 *
 * @author zhangpba
 * @date 2022-04-22
 */
public enum GoldEnum {

    /**
     * 上海期货交易价格
     */
    SHGOLD("shgold"),
    /**
     * 香港金银业贸易场价格
     */
    HKGOLD("hkgold"),
    /**
     * 银行账户黄金价格
     */
    BANK("bank"),
    /**
     * 伦敦金、银价格
     */
    LONDON("london");

    private String exchangeType;

    GoldEnum(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    /**
     * 判断是否存在枚举类中
     *
     * @param exchangeType
     * @return
     */
    public static boolean contains(String exchangeType) {
        for (GoldEnum goldEnum : GoldEnum.values()) {
            if (goldEnum.getExchangeType().equals(exchangeType)) {
                return true;
            }
        }
        return false;
    }
}
