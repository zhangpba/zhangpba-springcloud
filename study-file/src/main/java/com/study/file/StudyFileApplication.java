package com.study.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * 文件服务启动类
 *
 * @author zhangpba
 */
@SpringBootApplication
@EnableEurekaClient
public class StudyFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyFileApplication.class, args);
    }

}
