package com.study.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhangpba
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class StudyStreamApplication1 {

    public static void main(String[] args) {
        SpringApplication.run(StudyStreamApplication1.class, args);
    }
}
