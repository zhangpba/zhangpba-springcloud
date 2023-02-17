package com.data.chain.eventflow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventNodeDtlStatusEnum {
    // 节点处置状态
    NO_ACCEPT(0, "未接受"),
    ACCEPT(1, "处置中"),
    FINISH(2, "已处置");

    private final Integer status;
    private final String desc;

}
