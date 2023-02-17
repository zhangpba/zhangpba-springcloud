package com.data.chain.wechat.vo;

/**
 * @author zmingsong
 * @date 2022/12/15 16:36
 * @desc xxx
 */
public class TokenVo {
    private String corpId;
    private String corpSecret;

    TokenVo(){}
    public TokenVo(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }
}
