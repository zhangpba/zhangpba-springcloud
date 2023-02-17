package com.data.chain.eventflow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/20
 */
@Getter
@AllArgsConstructor
public enum ReceiverTypeEnum {

    // 接收人类型
    ENTERPRIS_MANAGER("ENTERPRISE_MANAGER", "企业负责人"),
    PROVINCE_SUPERVISOR("PROVINCE_SUPERVISOR", "省级局监察人员"),
    PROVINCE_MONITOR("PROVINCE_MONITOR", "省监管人员"),
    CITY_MONITOR("CITY_MONITOR", "市监管人员"),
    COUNTY_MONITOR("COUNTY_MONITOR", "县监管人员"),
    PRO_SYS_MAINTAINER("PRO_SYS_MAINTAINER", "省局级系统厂商运维人员");

    private final String type;
    private final String desc;

    public static String descOfReceiverType(String type){
        ReceiverTypeEnum[] values = ReceiverTypeEnum.values();
        for (ReceiverTypeEnum em:values){
            if(Objects.equals(em.getType(), type)) {
                return em.getDesc();
            }
        }
        return null ;
    }

}
