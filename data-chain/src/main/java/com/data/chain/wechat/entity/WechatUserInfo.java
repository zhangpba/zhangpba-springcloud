package com.data.chain.wechat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取访问用户身份返回值
 *
 * @author: wx
 * @date: 2021/12/31
 */
@NoArgsConstructor
@Data
public class WechatUserInfo {
    private Integer errcode;
    private String errmsg;
    private String UserId;
    private String DeviceId;
}
