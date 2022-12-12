package com.study.kafka.utils;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author zhangpba
 * @description 消费者
 * @date 2022/8/8
 */
@Component
public class ConsumerUtils {

    @Value("${kafka.ip}")
    private String ip;

    @Value("${kafka.port}")
    private int port;


    public KafkaConsumer<String, String> buildConsumer() {
        Properties props = new Properties();
        // 定义kakfa 服务的地址，不需要将所有broker指定上: 地址 = ip + 端口
        String address = ip + ":" + port;
        props.put("bootstrap.servers", address);
        // 制定consumer group
        props.put("group.id", "group-01");
        // 是否自动确认offset
        props.put("enable.auto.commit", "true");
        // 自动确认offset的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        // key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 定义consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }
}
