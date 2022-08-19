package com.study.kafka.service;

import org.apache.kafka.clients.admin.CreatePartitionsResult;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeConfigsResult;

import java.util.List;
import java.util.Set;

/**
 * @author zhangpba
 * @description TOPIC业务逻辑层
 * @date 2022/8/8
 */
public interface ITopicService {

    // 查看TOPIC名称列表
    Set<String> topicListNames();

    // 创建TOPIC
    CreateTopicsResult createTopic(String topicNames);

    // 删除TOPIC
    DeleteTopicsResult deleteTopic(List<String> topicNames);

    // 修改Topic的配置信息
    CreatePartitionsResult addPartitionsNum(String topicNames, int partitionsNum);

    // 查询Topic的配置信息
    DescribeConfigsResult describeConfig(List<String> topicNames);
}
