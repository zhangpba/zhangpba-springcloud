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
public enum NodeTypeEnum {

    // 节点类型
    DEAL_NODE(1, "处置节点"),
    SUPERVISION_NODE(2, "督导节点");

    private final Integer type;
    private final String desc;
}
