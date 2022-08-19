package com.study.kafka.run;

import com.study.kafka.Utils.ConsumerUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zhangpba
 * @description kafka消费者
 * @date 2022/8/8
 */
@Component
public class ConsumerRunner implements CommandLineRunner {

    // 监听的topic
    @Value("${kafka.topics}")
    private String topics;

    @Autowired
    private ConsumerUtils consumerUtils;

    @Override
    public void run(String... args) {
        KafkaConsumer<String, String> consumer = consumerUtils.buildConsumer();

        String[] topicsArray = topics.split(",");
        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Arrays.asList(topicsArray));

        while (true) {
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic:%s,offset:%d,消息:%s", record.topic(), record.offset(), record.value()));
            }
        }
    }
}
