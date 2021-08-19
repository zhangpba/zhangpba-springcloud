package com.study.eureka.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * eureka启动后，在控制台打印日志
 *
 * @date 2021-08-19
 */
@Component
public class EurekaRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(EurekaRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("欢迎使用study-eureka");
    }
}
