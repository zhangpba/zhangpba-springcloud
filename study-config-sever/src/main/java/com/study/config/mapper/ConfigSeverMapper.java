package com.study.config.mapper;

import com.study.starter.vo.config.ConfigInfo;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ConfigSeverMapper {

    /**
     * 根据条件查询配置信息
     *
     * @param configInfo 查询条件
     * @return 配置信息
     */
    ConfigInfo getConfigInfo(ConfigInfo configInfo);


    /**
     * 增加配置信息
     * @param configInfo
     */
    void addConfigInfo(ConfigInfo configInfo);
}
