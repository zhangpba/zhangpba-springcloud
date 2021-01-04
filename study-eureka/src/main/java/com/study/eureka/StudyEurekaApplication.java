package com.study.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhangpba
 */
@SpringBootApplication
@EnableEurekaServer
public class StudyEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyEurekaApplication.class, args);
    }

}
