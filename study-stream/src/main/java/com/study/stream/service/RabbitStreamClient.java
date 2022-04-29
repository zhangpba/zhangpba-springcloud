package com.study.stream.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * rabbit
 *
 * @author zhangpba
 * @date 2021-11-05
 */
public interface RabbitStreamClient {
    String INPUT = "myInput";
    String OUTPUT = "myOutput";

    @Input(RabbitStreamClient.INPUT)
    SubscribableChannel input();

    @Output(RabbitStreamClient.OUTPUT)
    MessageChannel output();
}
