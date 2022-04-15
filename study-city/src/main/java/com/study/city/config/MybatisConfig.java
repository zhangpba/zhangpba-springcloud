package com.study.city.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author zhangpba
 * @date 2022-04-13
 */
@Configuration
@MapperScan("com.study.**.mapper")
public class MybatisConfig {

}
