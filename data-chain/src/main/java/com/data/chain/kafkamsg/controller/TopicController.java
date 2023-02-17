package com.data.chain.kafkamsg.controller;

import com.data.chain.kafkamsg.service.ITopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.config.ConfigResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Topic控制类
 *
 * @author zhangpba
 */
@Api(value = "Topic控制类")
@RestController
@RequestMapping("/topic")
public class TopicController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private ITopicService topicService;

    /**
     * 查看TOPIC名称列表
     *
     * @return
     */
    @ApiOperation(value = "查看TOPIC名称列表")
    @RequestMapping(value = "/topicListNames", method = RequestMethod.GET)
    public Object topicListNames() {
        logger.info("查看TOPIC名称列表");
        return topicService.topicListNames();
    }

    /**
     * 创建TOPIC
     *
     * @param topicName 名称
     * @return
     */
    @ApiOperation(value = "创建TOPIC")
    @RequestMapping(value = "/createTopic", method = RequestMethod.GET)
    public Object createTopic(String topicName) {
        return topicService.createTopic(topicName);
    }

    /**
     * 删除TOPIC
     *
     * @param topicName 参数
     * @return
     */
    @ApiOperation(value = "删除TOPIC")
    @RequestMapping(value = "/deleteTopic", method = RequestMethod.GET)
    public Object deleteTopic(String topicName) {
        List<String> topicNames = new ArrayList<>();
        topicNames.add(topicName);
        return topicService.deleteTopic(topicNames);
    }

    /**
     * 修改TOPIC
     *
     * @param topicName     主题名称
     * @param partitionsNum partitions数量
     * @return
     */
    @ApiOperation(value = "修改TOPIC")
    @RequestMapping(value = "/upodateTopic", method = RequestMethod.GET)
    public Object upodateTopic(String topicName, int partitionsNum) {
        return topicService.addPartitionsNum(topicName, partitionsNum);
    }


    // 查询TOPIC的配置信息
    @ApiOperation(value = "查询TOPIC的配置信息")
    @RequestMapping(value = "/describeConfig", method = RequestMethod.GET)
    public Object describeConfig(@ApiParam(name = "topicName", value = "topicName", required = true) String topicName) {
        List<String> topicNames = new ArrayList<>();
        // 格式化入参
        String[] topics = topicName.split(",");
        topicNames.addAll(Arrays.asList(topics));
        DescribeConfigsResult describeConfigsResult = topicService.describeConfig(topicNames);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Map<ConfigResource, Config> map = describeConfigsResult.all().get();
            for (ConfigResource configResource : map.keySet()) {
                stringBuffer.append("TOPIC的配置信息 >>> name: " + configResource.name() + ", desc: " + map.get(configResource));
                stringBuffer.append(System.getProperty("line.separator"));
                stringBuffer.append(System.getProperty("line.separator"));
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
}
