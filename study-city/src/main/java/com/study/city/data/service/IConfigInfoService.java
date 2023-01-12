package com.study.city.data.service;

import com.study.city.data.entity.ConfigInfo;

import java.util.List;
import java.util.Map;

public interface IConfigInfoService {

    // 增加配置信息
    int addConfigInfo(ConfigInfo configInfo);

    // 获取配置信息
    List<ConfigInfo> getConfigInfos();

    // 获取配置信息
    Map<String,ConfigInfo> getConfigInfos(String application);

    // 跟进key和服务名称查询配置信息
    ConfigInfo getConfigInfo(String key, String application);
}