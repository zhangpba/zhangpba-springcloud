package com.study.config.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.study.**.mapper")
public class ApplicationConfig {
}
