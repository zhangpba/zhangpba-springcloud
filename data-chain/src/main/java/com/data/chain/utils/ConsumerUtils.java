package com.data.chain.utils;

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

    @Value("${kafka.host}")
    private String host;

    @Value("${kafka.consumer.group-id}")
    private String groupId;

    public KafkaConsumer<String, String> buildConsumer() {
        Properties props = new Properties();
        // 定义kakfa 服务的地址，不需要将所有broker指定上: 地址 = ip + 端口
        props.put("bootstrap.servers", host);
        // 制定consumer group
        props.put("group.id", groupId);
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
