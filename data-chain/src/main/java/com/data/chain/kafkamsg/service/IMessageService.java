package com.data.chain.kafkamsg.service;

/**
 * @author zhangpba
 * @description 消息业务逻辑层
 * @date 2022/8/8
 */
public interface IMessageService {

    // 发送消息
    void send(String topic, String message);

    // 发送消息-回调
    void sendCallBack(String topic, String message);

    // 接收消息
    void receive(String topics);
}
