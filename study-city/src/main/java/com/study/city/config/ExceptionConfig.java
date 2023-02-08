package com.study.city.config;

import com.study.common.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpba
 * @description 全局异常处理配置类
 * @date 2023/2/8
 */
@Configuration
public class ExceptionConfig {

    @Bean
    public GlobalExceptionHandler GlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
