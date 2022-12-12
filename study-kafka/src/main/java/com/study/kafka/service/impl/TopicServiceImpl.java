package com.study.kafka.service.impl;

import com.study.kafka.utils.TopicUtils;
import com.study.kafka.service.ITopicService;
import org.apache.kafka.clients.admin.CreatePartitionsResult;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangpba
 * @description TOPIC业务逻辑层
 * @date 2022/8/8
 */
@Service
public class TopicServiceImpl implements ITopicService {

    private static final Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Autowired
    private TopicUtils topicUtils;

    // 查看TOPIC名称列表
    @Override
    public Set<String> topicListNames() {
        Set<String> names = null;
        try {
            // 查询topic列表
            ListTopicsResult listTopicsResult = topicUtils.topicLists();
            // Topic的名称
            names = listTopicsResult.names().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return names;
    }


    @Override
    public CreateTopicsResult createTopic(String topicName) {
        CreateTopicsResult createTopicsResult = null;
        try {
            // 创建topic
            createTopicsResult = topicUtils.createTopic(topicName);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return createTopicsResult;

    }

    @Override
    public DeleteTopicsResult deleteTopic(List<String> topicNames) {
        // 删除topic
        DeleteTopicsResult deleteTopicsResult = topicUtils.delTopics(topicNames);
        return deleteTopicsResult;
    }

    @Override
    public CreatePartitionsResult addPartitionsNum(String topicNames, int partitionsNum) {
        CreatePartitionsResult createPartitionsResult = null;
        try {
            // 增加Partition数量
            createPartitionsResult = topicUtils.addPartitionsNum(topicNames, partitionsNum);
            System.out.println(createPartitionsResult.all().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return createPartitionsResult;
    }

    // 查看TOPIC描述信息
    public void describeTopics(String topicName) throws ExecutionException, InterruptedException {
        // 查看TOPIC描述信息
        List<String> topicNames = new ArrayList<>();
        topicNames.add(topicName);
        DescribeTopicsResult describeTopicsResult = topicUtils.describeTopics(topicNames);
        Map<String, TopicDescription> descriptionMap = describeTopicsResult.all().get();
        descriptionMap.forEach((key, value) ->
                System.out.println("查看TOPIC描述信息>>name: " + key + ", desc: " + value));
    }

    // 查询Topic的配置信息
    @Override
    public DescribeConfigsResult describeConfig(List<String> topicNames) {
        // 查询Topic的配置信息
        DescribeConfigsResult describeConfigsResult = topicUtils.describeConfig(topicNames);
        return describeConfigsResult;
    }
}
