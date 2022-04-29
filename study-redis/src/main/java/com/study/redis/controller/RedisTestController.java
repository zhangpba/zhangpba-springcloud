package com.study.redis.controller;

import com.study.redis.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 测试Red接口
 *
 * @author zhangpba
 * @date 2021-08-19
 */
@Api(value = "测试redis")
@RestController
public class RedisTestController {
    private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "redis的GET方法")
    @RequestMapping(value = "/get/{key}")
    public String getValue(@PathVariable(value = "key") @ApiParam(name = "redis的key") String key) {
        //查询缓存中是否存在
        boolean exists = redisUtils.exists(key);
        String str = "";
        if (exists) {
            //获取缓存
            Object object = redisUtils.get(key);
            logger.info("从缓存获取的数据:{}", object);
            str = object.toString();
        } else {
            //从数据库中获取信息
            logger.info("从数据库中获取数据");
            // 查询数据
            // str = testService.test();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(key, str, 10L, TimeUnit.MINUTES);
            logger.info("数据插入缓存" + str);
        }
        return str;
    }

    /**
     * 保存缓存数据
     *
     * @param key
     * @param value
     * @return
     */
    @ApiOperation(value = "保存缓存数据")
    @RequestMapping(value = "/put/{key}/{value}")
    public String putValue(@PathVariable(value = "key") @ApiParam(name = "redis的key") String key,
                           @PathVariable(value = "value") @ApiParam(name = "redis的值") String value) {
        // 查询缓存中是否存在
        boolean exists = redisUtils.exists(key);
        String str = "";
        if (!exists) {
            // 保存缓存
            Object object = redisUtils.set(key, value, -1L, TimeUnit.DAYS);
            logger.info("从缓存获取的数据" + object);
            str = object.toString();
        }
        return "保存成功！";
    }

    /**
     * study-reids服务,启动验证
     *
     * @return
     */
    @ApiOperation(value = "")
    @GetMapping(value = "/index")
    public String index() {
        logger.info("study-reids服务,启动 success!");
        return "study-reids服务,启动 success!";
    }
}
