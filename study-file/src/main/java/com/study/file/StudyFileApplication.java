package com.study.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


/**
 * 文件服务启动类
 *
 * @author zhangpba
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class StudyFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyFileApplication.class, args);
    }

}
