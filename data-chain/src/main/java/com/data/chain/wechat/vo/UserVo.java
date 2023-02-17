package com.data.chain.wechat.vo;

/**
 * @author zmingsong
 * @date 2022/12/15 18:49
 * @desc 获取用户信息请求
 */
public class UserVo {
    private String code;
    private String userId;
    private String accessToken;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
