package com.study.city.data.service;

import com.study.city.data.entity.response.PyqResponse;

import java.util.List;
import java.util.Map;

public interface ITxPyqService {
    // 获取朋友圈文案
    @Deprecated
    Map<String, String> getPyqWenan();

    // 获取古典名句
    @Deprecated
    Map<String, String> getGdmj();

    // 获取土味情话
    @Deprecated
    Map<String, String> getSayLove();

    // 发送朋友圈文案邮件
    void sendPyqEmail();

    // 发送古典名句邮件
    void sendGdmjEmail();

    // 发送土味情话
    void sendSayLoveEmail();


    // 获取朋友圈文案
    List<PyqResponse> pyqWenan();

    // 获取古典名句
    List<PyqResponse> gdmj();

    // 获取土味情话
    List<PyqResponse> sayLove();
}


