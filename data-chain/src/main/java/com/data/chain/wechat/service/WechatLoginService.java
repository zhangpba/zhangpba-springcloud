package com.data.chain.wechat.service;

import com.data.chain.wechat.entity.TokenModel;
import com.data.chain.wechat.entity.WechatDeptReturn;
import com.data.chain.wechat.entity.WechatUserDetail;
import com.data.chain.wechat.entity.WechatUserInfo;
import com.data.chain.wechat.vo.TokenVo;
import com.data.chain.wechat.vo.UserVo;

/**
 * @author zmingsong
 * @date 2022/12/15 16:39
 * @desc xxx
 */
public interface WechatLoginService {
    /**
     * 获取access_token
     * @param vo
     * @return
     */
    TokenModel getAccessToken(TokenVo vo);

    /**
     * 获取用户基本信息，主要用于获取用户id
     * @param vo
     * @return
     */
    WechatUserInfo getWechatUserInfo(UserVo vo);

    /**
     * 通过userId和accessToken获取用户信息
     * @param vo
     * @return
     */
    WechatUserDetail getWechatUserDetail(UserVo vo);

    /**
     * 根据code，accessToken获取用户明细信息，浏览器企微认证时使用
     * @param vo
     * @return
     */
    WechatUserDetail getUserDetail(UserVo vo);

    WechatDeptReturn getWechatDeptById(String accessToken, String deptId);
}
