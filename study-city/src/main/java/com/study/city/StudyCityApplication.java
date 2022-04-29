package com.study.city;

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
public class StudyCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyCityApplication.class, args);
    }

}
