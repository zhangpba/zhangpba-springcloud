package com.study.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 配置中心启动类
 *
 * @author zhangpba
 * @date 2021-08-22
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class StudyConfigSeverApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyConfigSeverApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        //return new RestTemplate();
        //用第三方的通信组件okhttp
        return new RestTemplate();
    }


}
