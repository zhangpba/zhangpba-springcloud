package com.study.kafka.utils;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author zhangpba
 * @description 生产者帮助类
 * @date 2022/8/8
 */
@Component
public class ProducerUtils {

    @Value("${kafka.ip}")
    private String ip;

    @Value("${kafka.port}")
    private int port;

    /**
     * 创建一个生产者对象
     *
     * @return
     */
    public KafkaProducer<String, String> buildProducer() {
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号：地址 = ip + 端口
        String address = ip + ":" + port;
        props.put("bootstrap.servers", address);
        // 等待所有副本节点的应答
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }

    /**
     * 方式一 生产数据
     *
     * @param producer 生产者对象
     * @param topic    主题
     * @param message  消息
     */
    public void send(Producer<String, String> producer, String topic, String message) {
        producer.send(new ProducerRecord<String, String>(topic, message));
        // 关闭连接
        producer.close();
    }

    /**
     * 方式二 生产数据-回调
     *
     * @param producer
     * @date 2020-06-16
     */
    public void sendCallBack(Producer<String, String> producer, String topic, String messahe) {
        try {
            producer.send(new ProducerRecord<String, String>(topic, messahe), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (metadata != null) {
                        System.out.println(metadata.partition() + "----" + metadata.offset());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
