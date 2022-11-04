package com.study.zookeeper.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试starter
 *
 * @author zhangpba
 * @date 2022-04-11
 */
@Api(value = "测试starter")
@RestController
public class ZookeeperController {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperController.class);

    @Autowired
    private CuratorFramework curatorFramework;

    @ApiOperation(value = "测试index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        logger.info("study-zookeeper服务，starter方法");
        // 增加持久化节点
        try {
            String path = curatorFramework.create().forPath("/curator-node");
            logger.info(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "测试index";
    }

}
