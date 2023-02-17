package com.data.chain.wechat.service.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.data.chain.utils.RedisUtils;
import com.data.chain.wechat.common.CurrentThreadLocal;
import com.data.chain.wechat.common.WeChatConstants;
import com.data.chain.wechat.common.WechatProperties;
import com.data.chain.wechat.entity.*;
import com.data.chain.wechat.service.WechatLoginService;
import com.data.chain.wechat.util.StringUtils;
import com.data.chain.wechat.vo.TokenVo;
import com.data.chain.wechat.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zmingsong
 * @date 2022/12/15 16:39
 * @desc xxx
 */
@Slf4j
@Service
public class WechatServiceImpl implements WechatLoginService {
    @Autowired
    private WechatProperties properties;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public TokenModel getAccessToken(TokenVo vo) {
        // 获取第三方应用凭证url
        String accessTokenUrl = properties.getAccessTokenUrl();
        // 	第三方应用access_token
        String accessToken = "";
        TokenModel weChat = null;
        try {
            //判断corpID和corpSerect为空时直接读取本地文件
            if (StringUtils.isEmpty(vo.getCorpId() )) {
                vo.setCorpId(properties.getCorpId());
            }
            if(StringUtils.isEmpty(vo.getCorpSecret())){
                vo.setCorpSecret(properties.getCorpSecret());
            }
            Map<String, Object> map = new HashMap<>();
            //以ww或wx开头应用id
            map.put("corpid", vo.getCorpId());
            //应用secret
            map.put("corpsecret", vo.getCorpSecret());
            String body = HttpRequest.get(accessTokenUrl).body(JSONUtil.toJsonStr(map), ContentType.JSON.getValue()).execute().body();
            weChat = JSONUtil.toBean(body, TokenModel.class);
            if (weChat.getErrcode() == null || weChat.getErrcode() == 0) {
                accessToken = weChat.getAccess_token();
                Long expires_in = weChat.getExpires_in();
                //将token存到本地缓存
                redisUtils.set(WeChatConstants.ACCESS_TOKEN_EN, accessToken);
                redisUtils.set(WeChatConstants.EXPIRES_IN_EN, expires_in);
            }
            // 打印消息
        } catch (Exception e) {
            log.error("获取access token失败errcode:" + accessToken);
            throw new RuntimeException();
        }
        return weChat;
    }

    @Override
    public WechatUserInfo getWechatUserInfo(UserVo vo) {
        // 获取第三方应用凭证url
        String userInfoUrl = properties.getUserInfoUrl();
        // 	第三方应用用户信息
        WechatUserInfo weChat = null;
        try {
            userInfoUrl += "?code=" + vo.getCode() + "&access_token=" + vo.getAccessToken();
            String body = HttpRequest.get(userInfoUrl).execute().body();
            weChat = JSONUtil.toBean(body, WechatUserInfo.class);
        } catch (Exception e) {
            log.error("获取用户基本信息失败errcode:" + vo.getAccessToken());
            throw new RuntimeException();
        }
        return weChat;
    }

    @Override
    public WechatUserDetail getWechatUserDetail(UserVo vo) {
        // 获取第三方应用凭证url
        String userDetailUrl = properties.getUserDetailUrl();
        // 	第三方应用用户信息
        WechatUserDetail weChat = null;
        try {
            userDetailUrl += "?userid=" + vo.getUserId() + "&access_token=" + vo.getAccessToken();
            String body = HttpRequest.get(userDetailUrl).execute().body();
            weChat = JSONUtil.toBean(body, WechatUserDetail.class);
            handleUserDetail(weChat,vo.getAccessToken());
        } catch (Exception e) {
            log.error("获取用户基本信息失败errcode:" + vo.getAccessToken());
            throw new RuntimeException();
        }
        return weChat;
    }

    @Override
    public WechatUserDetail getUserDetail(UserVo vo) {
        WechatUserDetail wechatUserDetail = null;
        if (vo != null) {
            WechatUserInfo info = this.getWechatUserInfo(vo);
            if (info != null) {
                vo.setUserId(info.getUserId());
                wechatUserDetail = this.getWechatUserDetail(vo);
            }
        }
        return wechatUserDetail;
    }

    @Override
    public WechatDeptReturn getWechatDeptById(String accessToken, String deptId) {
        // 获取第三方应用凭证url
        String userOneDeptUrl = properties.getUserOneDeptUrl();
        // 	第三方应用用户信息
        WechatDeptReturn weChat = null;
        try {
            userOneDeptUrl += "?access_token=" + accessToken + "&id=" + deptId;
            String body = HttpRequest.get(userOneDeptUrl).execute().body();
            weChat = JSONUtil.toBean(body, WechatDeptReturn.class);
        } catch (Exception e) {
            log.error("获取部门基本信息errcode: " + deptId);
            throw new RuntimeException();
        }
        return weChat;
    }
    //处理微信返回的用户信息，翻译补充相关部门信息，然后放入后台内存中
    private void handleUserDetail(WechatUserDetail wechatUserDetail,String accessToken){
        if (wechatUserDetail != null) {
            for(String deptId : wechatUserDetail.getDepartment()){
                WechatDeptReturn dept=this.getWechatDeptById(accessToken,deptId);
                if(dept!=null && dept.getDepartment()!=null){
                    WechatDepartment department= dept.getDepartment();
                    List<WechatDepartment> userDetailDepts=wechatUserDetail.getDepts();
                    if(userDetailDepts!=null && !userDetailDepts.isEmpty()){
                        userDetailDepts.add(department);
                    }else{
                        List<WechatDepartment> newDepartments=new ArrayList<>();
                        newDepartments.add(department);
                        wechatUserDetail.setDepts(newDepartments);
                    }
                }
            }
            CurrentThreadLocal.set(wechatUserDetail);
        }
    }
}
