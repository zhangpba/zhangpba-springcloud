package com.data.chain.wechat.entity;

import lombok.Data;

/**
 * @author zmingsong
 * @date 2022/12/20 15:30
 * @desc xxx
 */
@Data
public class WechatDeptReturn {
    private Integer errcpde;
    private String errmsg;
    private WechatDepartment department;
}
