package com.data.chain.eventflow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/20
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum {

    // 业务类型
    CH4_RISK("CH4_RISK", "瓦斯重大风险信息"),
    CO_RISK("CO_RISK", "CO重大风险信息"),
    DATA_INTERRUPT_1H("DATA_INTERRUPT_1H", "单次数据传输中断超1小时"),
    MINE_OVERMAN("MINE_OVERMAN", "井下超员"),
    CH4_MUTATION("CH4_MUTATION", "瓦斯突变"),
    CO_MUTATION("CO_MUTATION", "CO突变"),
    ON_DUTY_VACANCY("ON_DUTY_VACANCY", "带班空岗"),
    ILLEGAL_PRODUCE_ANALYSIS("ILLEGAL_PRODUCE_ANALYSIS", "违规生产分析结果");

    private final String type;
    private final String desc;

    public static String descOfBusinessType(String type){
        BusinessTypeEnum[] values = BusinessTypeEnum.values();
        for (BusinessTypeEnum em:values){
            if(em.getType().equals(type)) {
                return em.getDesc();
            }
        }
        return "" ;
    }

}
