package com.data.chain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/20
 */
@Getter
@AllArgsConstructor
public enum IsDeleteEnum {

    /**
     * 删除状态标识
     *
     */
    NORMAL(0, "正常"),
    DELETED(1, "已删除");


    private final Integer status;
    private final String desc;
}
