package com.study.reids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangpba
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker // 开启断路器
public class StudyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyUserApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        //return new RestTemplate();
        //用第三方的通信组件okhttp
        return new RestTemplate();
    }


}
