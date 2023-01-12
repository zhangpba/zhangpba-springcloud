package com.study.city.data.controller;

import com.study.city.data.service.IConfigInfoService;
import com.study.city.data.entity.ConfigInfo;
import com.study.common.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * 配置类
 *
 * @author zhangpba
 * @date 2022-06-21
 */
@Api(value = "配置类", tags = "配置类")
@RestController
@RequestMapping("/config")
public class ConfigInfoController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigInfoController.class);

    @Autowired
    private IConfigInfoService configInfoService;

    /**
     * 配置信息
     *
     * @return
     */
    @ApiOperation(value = "配置信息")
    @GetMapping(value = "/all-configs")
    public ResponseMessage allConfigs() {
        return ResponseMessage.success(configInfoService.getConfigInfos());
    }

    /**
     * 配置信息
     *
     * @return
     */
    @ApiOperation(value = "配置信息")
    @GetMapping(value = "/config-map")
    public ResponseMessage configMap(@ApiParam(name = "key", required = true) @RequestParam String key,
                                     @ApiParam(name = "application", required = true) @RequestParam String application) {
        return ResponseMessage.success(configInfoService.getConfigInfos(application));
    }


    /**
     * 增加配置信息
     *
     * @return
     */
    @ApiOperation(value = "增加配置信息")
    @PostMapping(value = "/save-config")
    public ResponseMessage saveConfig(@RequestBody ConfigInfo configInfo) {
        configInfo.setCreateDate(new Date());
        configInfoService.addConfigInfo(configInfo);
        return ResponseMessage.success();
    }
}
