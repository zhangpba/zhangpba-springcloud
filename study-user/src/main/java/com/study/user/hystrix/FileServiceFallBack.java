package com.study.user.hystrix;

import com.study.starter.vo.user.User;
import com.study.user.feign.FileServiceFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 断路器-降级处理
 *
 * @date 2021-08-26
 */
@Component
public class FileServiceFallBack implements FileServiceFeign {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceFallBack.class);

    @Override
    public String getFileHost(String name) {
        logger.info("getFileHost 进行降级处理！:{}", name);
        return "getFileHost 进行降级处理！";
    }

    @Override
    public String postFileHost(User user) {
        logger.info("postFileHost 进行降级处理！:{}", user);
        return "postFileHost 进行降级处理！";
    }
}
