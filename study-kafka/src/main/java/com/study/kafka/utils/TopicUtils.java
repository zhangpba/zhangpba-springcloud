package com.study.kafka.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.AlterConfigOp;
import org.apache.kafka.clients.admin.AlterConfigsResult;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
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
import java.util.Collection;
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

    @Value("${kafka.ip}")
    private String ip;

    @Value("${kafka.port}")
    private int port;

    /**
     * 配置并创建AdminClient
     *
     * @return
     */
    public AdminClient createAdminClient(String ip, int port) {
        Properties properties = new Properties();
        // 地址 = ip + 端口
        String address = ip + ":" + port;
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        // 创建AdminClient实例
        return AdminClient.create(properties);
    }

    // 创建AdminClient实例
    public AdminClient createAdminClient() {
        return createAdminClient(ip, port);
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
     * 修改Topic的配置信息
     */
    public AlterConfigsResult alterConfig() throws ExecutionException, InterruptedException {
        AdminClient adminClient = createAdminClient();
        // 指定ConfigResource的类型及名称
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, "zhangpba-topic");
        // 配置项以ConfigEntry形式存在
        List list = new ArrayList<>();
        list.add(new ConfigEntry("preallocate", "true"));
        Config config = new Config(list);

        Map<ConfigResource, Config> configMaps = new HashMap<>();
        configMaps.put(configResource, config);
        AlterConfigsResult result = adminClient.alterConfigs(configMaps);
        System.out.println("修改Topic的配置信息:" + result.all().get());
        return result;
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

    /**
     * 修改Topic的配置信息
     */
    public AlterConfigsResult incrementalAlterConfig() throws ExecutionException, InterruptedException {
        AdminClient adminClient = createAdminClient();
        // 指定ConfigResource的类型及名称
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, "zhangpba-topic");
        // 配置项同样以ConfigEntry形式存在，只不过增加了操作类型
        // 以及能够支持操作多个配置项，相对来说功能更多、更灵活
        Collection<AlterConfigOp> configs = new ArrayList<>();
        configs.add(new AlterConfigOp(new ConfigEntry("preallocate", "false"), AlterConfigOp.OpType.SET));

        Map<ConfigResource, Collection<AlterConfigOp>> configMaps = new HashMap<>();
        configMaps.put(configResource, configs);
        AlterConfigsResult result = adminClient.incrementalAlterConfigs(configMaps);
        System.out.println("修改Topic的配置信息:" + result.all().get());
        return result;
    }
}
