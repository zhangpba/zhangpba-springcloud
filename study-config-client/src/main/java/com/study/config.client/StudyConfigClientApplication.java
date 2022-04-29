package com.study.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author zhangpba
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class StudyConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyConfigClientApplication.class, args);
    }
}
