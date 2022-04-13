package com.study.redis.controller;

import com.study.redis.RedisUtils;
import com.study.starter.utils.FirstNameUtils;
import com.study.starter.vo.BaiJiaXingVo;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 百家姓控制类
 *
 * @author zhangpba
 * @date 2022-04-12
 */
@Api(value = "百家姓控制类")
@RestController
public class BaiJiaXingController {
    private static final Logger logger = LoggerFactory.getLogger(BaiJiaXingController.class);

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 百家姓 存储到redis中
     *
     * @return
     */
    @ApiOperation(value = "百家姓存储到redis中")
    @PutMapping(value = "/setNames")
    public ResponseMessage setNames() {
        // 获取百家姓
        List<BaiJiaXingVo> list = FirstNameUtils.getBaijaiXing();
        redisUtils.set("baijiaxing", list);
        return ResponseMessage.success("百家姓存储到redis中!");
    }

    /**
     * 获取缓存中的百家姓
     *
     * @return
     */
    @ApiOperation(value = "获取缓存中的百家姓")
    @GetMapping(value = "/getNames")
    public ResponseMessage getNames() {
        return ResponseMessage.success(redisUtils.get("baijiaxing"));
    }
}
