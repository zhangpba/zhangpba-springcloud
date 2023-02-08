package com.study.city.data.service;

import com.study.city.data.entity.response.QQResponse;

/**
 * @author zhangpba
 * @description QQ用户信息
 * @date 2023/2/7
 */
public interface IQQService {

    QQResponse getQQInfo(String QQ);
}
