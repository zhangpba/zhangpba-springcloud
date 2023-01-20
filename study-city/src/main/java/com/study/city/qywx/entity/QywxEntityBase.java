package com.study.city.qywx.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpba
 * @description 企业微信返回基础信息
 * @date 2023/1/17
 */
@Setter
@Getter
public class QywxEntityBase {

    private Long errcode;
    private String errmsg;
    private String access_token;
    private String expires_in;
}
