package com.study.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author zhangpba
 */
@SpringBootApplication
@EnableEurekaClient
public class StudyFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyFileApplication.class, args);
    }

}
