package com.study.city.service;

import java.util.Map;

public interface IPyqService {
    // 获取朋友圈文案
    Map<String, String> getPyqWenan();

    // 获取古典名句
    Map<String, String> getGdmj();

    // 获取土味情话
    Map<String, String> getSayLove();

    // 发送朋友圈文案邮件
    void sendPyqEmail();

    // 发送古典名句邮件
    void sendGdmjEmail();

    // 发送土味情话
    void sendSayLoveEmail();
}


