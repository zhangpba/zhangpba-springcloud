package com.study.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhangpba
 * @description 配置中心-配置信息模型
 * @date 2022/6/18
 */
@Getter
@Setter
public class ConfigInfo {
    private Integer id;
    private String key;
    private String value;
    private String application;
    private String profile;
    private String label;
    private Date createDate;
}
