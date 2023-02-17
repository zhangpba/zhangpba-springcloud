package com.data.chain.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreatePartitionsResult;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewPartitions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangpba
 * @description Topic帮助类
 * @date 2022/8/8
 */
@Component
public class TopicUtils {

    @Value("${kafka.host}")
    private String host;

    /**
     * 配置并创建AdminClient
     *
     * @return
     */
    public AdminClient createAdminClient() {
        Properties properties = new Properties();
        // 地址 = ip + 端口
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, host);

        // 创建AdminClient实例
        return AdminClient.create(properties);
    }

    /**
     * 创建topic
     *
     * @param topicName topic名称
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public CreateTopicsResult createTopic(String topicName) throws ExecutionException, InterruptedException {
        AdminClient adminClient = createAdminClient();
        // partition数量
        int numPartitions = 1;
        // 副本数量
        short replicationFactor = 1;

        List<NewTopic> list = new ArrayList<>();
        NewTopic topic = new NewTopic(topicName, numPartitions, replicationFactor);
        list.add(topic);
        CreateTopicsResult result = adminClient.createTopics(list);
        return result;
    }

    /**
     * 查询Topic列表
     */
    public ListTopicsResult topicLists() throws ExecutionException, InterruptedException {
        AdminClient adminClient = createAdminClient();
        ListTopicsResult result = adminClient.listTopics();
        return result;
    }

    /**
     * 删除Topic
     */
    public DeleteTopicsResult delTopics(List<String> topicNames) {
        AdminClient adminClient = createAdminClient();
        DeleteTopicsResult result = null;
        if (topicNames != null && !topicNames.isEmpty()) {
            result = adminClient.deleteTopics(topicNames);
        }
        return result;
    }

    /**
     * 查询Topic的描述信息
     *
     * @param topicNames topic名称集合
     * @return
     */
    public DescribeTopicsResult describeTopics(List<String> topicNames) {
        AdminClient adminClient = createAdminClient();
        DescribeTopicsResult result = null;
        if (topicNames != null && !topicNames.isEmpty()) {
            result = adminClient.describeTopics(topicNames);
        }
        return result;

    }

    /**
     * 查询Topic的配置信息
     *
     * @param topicNames topic名称集合
     * @return
     */
    public DescribeConfigsResult describeConfig(List<String> topicNames) {
        AdminClient adminClient = createAdminClient();
        List<ConfigResource> list = new ArrayList<>();
        if (topicNames != null && !topicNames.isEmpty()) {
            for (String topic : topicNames) {
                ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
                list.add(configResource);
            }
        }
        DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(list);
        return describeConfigsResult;
    }

    /**
     * 增加Partition数量，目前Kafka不支持删除或减少Partition，只能增加
     */
    public CreatePartitionsResult addPartitionsNum(String topicName, int partitionsNum) throws ExecutionException, InterruptedException {
        AdminClient adminClient = createAdminClient();
        Map<String, NewPartitions> newPartitions = new HashMap<>();
        // 将MyTopic的Partition数量调整为2
        newPartitions.put(topicName, NewPartitions.increaseTo(partitionsNum));
        CreatePartitionsResult result = adminClient.createPartitions(newPartitions);
        return result;
    }
}
