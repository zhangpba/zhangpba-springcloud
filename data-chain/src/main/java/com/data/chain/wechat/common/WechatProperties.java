package com.data.chain.wechat.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zmingsong
 * @date 2022/12/15 17:25
 * @desc xxx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class WechatProperties {
    @Value("${qywx.config.corpId}")
    private String corpId;
    @Value("${qywx.config.corpSecret}")
    private String corpSecret;
    //用户agentid
    @Value("${qywx.config.agentId}")
    private String agentId;
    //获取用户token详情
    @Value("${qywx.url.accessTokenUrl}")
    private String accessTokenUrl;
    //获取用户基础详情地址
    @Value("${qywx.url.userInfoUrl}")
    private String userInfoUrl;
    //获取用户详情地址
    @Value("${qywx.url.userDetailUrl}")
    private String userDetailUrl;
    //获取单个部门信息接口
    @Value("${qywx.url.userOneDeptUrl}")
    private String userOneDeptUrl;
}
