package com.study.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpba
 * @description 配置类
 * @date 2022/10/28
 */
@Configuration
public class CuratorConfig {

    @Autowired
    private WrapperZK wrapperZK;

    /**
     * 初始化的时候开启连接
     *
     * @return
     */
    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
                wrapperZK.getConnectString(),
                wrapperZK.getSessionTimeoutMs(),
                wrapperZK.getConnectionTimeoutMs(),
                new RetryNTimes(wrapperZK.getRetryCount(), wrapperZK.getElapsedTimeMs())
        );
    }
}
