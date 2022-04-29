package com.study.starter.config;

import com.study.starter.properties.DemoProperties;
import com.study.starter.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类-demo
 *
 * @author zhangpba
 * @date 2022-04-11
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@ConditionalOnProperty(prefix = "demo", name = "isopen", havingValue = "true")
public class DemoConfig {

    @Autowired
    private DemoProperties demoProperties;

    @Bean(name = "demoService")
    public DemoService demoService() {
        return new DemoService(demoProperties.getSayWhat(), demoProperties.getToWho());
    }

}
