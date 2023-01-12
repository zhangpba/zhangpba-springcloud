package com.study.city.data.service.impl;

import com.study.city.data.mapper.ConfigInfoMapper;
import com.study.city.data.service.IConfigInfoService;
import com.study.city.data.entity.ConfigInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置信息业务层
 *
 * @author zhangpba
 * @date 2022-06-21
 */
@Service
public class ConfigInfoServiceImpl implements IConfigInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigInfoServiceImpl.class);

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Override
    public int addConfigInfo(ConfigInfo configInfo) {
        return configInfoMapper.addConfigInfo(configInfo);
    }

    @Override
    public List<ConfigInfo> getConfigInfos() {
        List<ConfigInfo> list = configInfoMapper.queryConfigInfos(new ConfigInfo());
        return list;
    }

    // 获取配置信息
    @Override
    public Map<String, ConfigInfo> getConfigInfos(String application) {
        Map<String, ConfigInfo> map = new HashMap<String, ConfigInfo>();
        ConfigInfo c = new ConfigInfo();
        c.setApplication(application);
        List<ConfigInfo> list = configInfoMapper.queryConfigInfos(c);
        if (list != null && !list.isEmpty()) {
            for (ConfigInfo configInfo : list) {
                map.put(configInfo.getKey(), configInfo);
            }
        }
        return map;
    }

    @Override
    public ConfigInfo getConfigInfo(String key, String application) {
        ConfigInfo c = new ConfigInfo();
        c.setApplication(application);
        c.setKey(key);
        ConfigInfo configInfo = configInfoMapper.queryConfigInfo(c);
        return configInfo;
    }
}