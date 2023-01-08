package com.study.file.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.study.file.service.FileService;
import com.study.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @HystrixCommand(fallbackMethod = "getFileHostError")
    @Override
    public String getFileHost(String name) {
        logger.info("进入feign服务提供者FileServiceImpl：getFileHost");
        // 发生异常
        int a = 1 / 0;
        return "我是file服务get请求返回的数据:" + name;
    }

    @Override
    public String postFileHost(User user) {
        logger.info("进入feign服务提供者FileServiceImpl：postFileHost");
        return "我是file服务post请求返回的数据: " + user;
    }

    /**
     * 当getFileHost发生异常的时候，调用熔断方法
     *
     * @param name 入参
     * @return 返回对象
     * @date 2021-08-26
     */
    public String getFileHostError(String name) {
        logger.info("进入熔断阶段：{}", name);
        return "进入熔断阶段：" + name;
    }
}
