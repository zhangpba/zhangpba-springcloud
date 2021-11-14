package com.study.stream.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * rabbit接收数据
 *
 * @author zhangpba
 * @date 2021-11-05
 */
@EnableBinding(value = {RabbitStreamClient.class})
@Service
public class RabbitStreamReceiver {

    private Logger logger = LoggerFactory.getLogger(RabbitStreamReceiver.class);

    @StreamListener(RabbitStreamClient.INPUT)
    public void receive(String message) {
        logger.info("消费到的数据: {}", message);
    }

}
