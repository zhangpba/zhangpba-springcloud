package com.study.kafka.service.impl;

import com.study.kafka.utils.ConsumerUtils;
import com.study.kafka.utils.ProducerUtils;
import com.study.kafka.service.IMessageService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author zhangpba
 * @description 生产者业务逻辑层
 * @date 2022/8/8
 */
@Service
public class MessageServiceImpl implements IMessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private ProducerUtils producerUtils;

    @Autowired
    private ConsumerUtils consumerUtils;

    @Override
    public void send(String topic, String message) {
        KafkaProducer<String, String> producer = producerUtils.buildProducer();
        // 不回调
        producerUtils.send(producer, topic, message);
    }

    @Override
    public void sendCallBack(String topic, String message) {
        KafkaProducer<String, String> producer = producerUtils.buildProducer();
        // 回调
        producerUtils.sendCallBack(producer, topic, message);

    }

    @Override
    public void receive(String topics) {
        KafkaConsumer<String, String> consumer = consumerUtils.buildConsumer();
        String[] strings = topics.split(",");
        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Arrays.asList(strings));
        // 读取数据，读取超时时间为100ms
        ConsumerRecords<String, String> records = consumer.poll(100);

        for (ConsumerRecord<String, String> record : records) {
            System.out.println(String.format("topic:%s,offset:%d,消息:%s", record.topic(), record.offset(), record.value()));
        }
    }
}
