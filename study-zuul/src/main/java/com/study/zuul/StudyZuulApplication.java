package com.study.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zhangpba
 * @EnableZuulProxy - 开启Zuul网关。
 * 当前应用是一个Zuul微服务网关。会在Eureka注册中心中注册当前服务。并发现其他的服务。
 * Zuul需要的必要依赖是spring-cloud-starter-zuul。
 *
 * exclude = DataSourceAutoConfiguration.class 是排除掉datasource的自动注解 2022-04-25
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
public class StudyZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyZuulApplication.class, args);
    }

}
