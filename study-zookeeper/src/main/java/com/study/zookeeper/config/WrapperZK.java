package com.study.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/10/28
 */
@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class WrapperZK {

    private int retryCount;
    private int elapsedTimeMs;
    private String connectString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}
