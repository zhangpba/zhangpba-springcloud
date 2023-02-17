package com.data.chain.eventflow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventStatusEnum {
    // 事件处置状态
    NO_ACCEPT(0, "未处置"),
    ACCEPT(1, "处置中"),
    FINISH(2, "已处置");

    private final Integer status;
    private final String desc;

}
