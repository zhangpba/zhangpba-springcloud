package com.study.city.data.controller;

import com.study.city.data.entity.response.QQResponse;
import com.study.city.data.service.IQQService;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpba
 * @description QQ信息控制层
 * @date 2022/7/9
 */
@Api(value = "QQ信息", tags = "QQ信息")
@RestController
@RequestMapping("/qq")
public class QQController {
    private static final Logger logger = LoggerFactory.getLogger(QQController.class);

    @Autowired
    private IQQService qqService;

    /**
     * 查询当日黄金
     *
     * @param QQ QQ号码
     * @return
     */
    @ApiOperation(value = "查询QQ信息")
    @GetMapping(value = "/getQQInfo")
    public ResponseMessage getQQInfo(@ApiParam(name = "QQ", value = "QQ号码", required = true) @RequestParam String QQ) {
        QQResponse qqResponse = qqService.getQQInfo(QQ);
        return ResponseMessage.success(qqResponse);
    }
}
