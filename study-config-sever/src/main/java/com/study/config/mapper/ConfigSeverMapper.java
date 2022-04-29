package com.study.config.mapper;

import com.study.config.model.ConfigInfo;
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
}
