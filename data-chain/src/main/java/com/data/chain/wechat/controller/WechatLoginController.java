package com.data.chain.wechat.controller;

import com.data.chain.wechat.common.CurrentThreadLocal;
import com.data.chain.wechat.entity.TokenModel;
import com.data.chain.wechat.entity.WechatUserDetail;
import com.data.chain.wechat.entity.WechatUserInfo;
import com.data.chain.wechat.service.WechatLoginService;
import com.data.chain.wechat.vo.TokenVo;
import com.data.chain.wechat.vo.UserVo;
import com.data.chain.wechat.vo.WeChatVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zmingsong
 * @date 2022/12/15 16:28
 * @desc xxx
 */
@Api("用户信息接口")
@RestController
@CrossOrigin
@RequestMapping("/wechat")
public class WechatLoginController {
    private static final Logger logger = LoggerFactory.getLogger(WechatLoginController.class);

    @Autowired
    private WechatLoginService wechatLoginService;

    @ApiOperation(value = "获取企业微信token接口" ,  notes="获取企业微信token接口")
    @PostMapping("/assessToken")
    public TokenModel fetchAccessToken(@RequestBody TokenVo req){
        TokenModel model=wechatLoginService.getAccessToken(req);
        return model;
    }
    @ApiOperation(value = "获取个人基本信息接口" ,  notes="获取个人基本信息接口")
    @PostMapping("/getUserInfo")
    public WechatUserInfo fetchUserInfo(@RequestBody UserVo model){
        WechatUserInfo info=wechatLoginService.getWechatUserInfo(model);
        return info;
    }
    @ApiOperation(value = "根据userId和token获取个人详情接口" ,  notes="获取个人详情接口")
    @PostMapping("/getUserDetail")
    public WechatUserDetail fetchUserDetail(@RequestBody UserVo model){
        WechatUserDetail info=wechatLoginService.getWechatUserDetail(model);
        return info;
    }
    @ApiOperation(value = "根据code和token执行获取用户信息整体接口" ,  notes="获取个人详情接口")
    @PostMapping("/execLogin")
    public WechatUserDetail execLogin(@RequestBody WeChatVo model){
        UserVo vo=new UserVo();
        vo.setAccessToken(model.getAccess_token());
        vo.setCode(model.getCode());
        WechatUserDetail userDetail=wechatLoginService.getUserDetail(vo);
        return userDetail;
    }
}
