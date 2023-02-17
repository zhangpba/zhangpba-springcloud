package com.data.chain.cardmanage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/14
 */
@Getter
@AllArgsConstructor
public enum HandleStatusEnum {
    // 卡片处置状态
    NO_ACCEPT(0, "未接受"),
    ACCEPT(1, "已接受"),
    FINISH(2, "已处置");

    private final Integer status;
    private final String desc;

}
