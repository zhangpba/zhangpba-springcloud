package com.data.chain;

import com.data.chain.config.SpringBeanUtils;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 数据链启动类
 *
 * @author zhangpba
 */
@MapperScan(value = "com.data.chain.**.dao")
@SpringBootApplication
//@EnableEurekaClient
//@EnableDiscoveryClient
@EnableSwaggerBootstrapUI
public class DataChainApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DataChainApplication.class, args);
        SpringBeanUtils.setApplicationContext(context);

    }
}
