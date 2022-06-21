package com.study.city.mapper;

import com.study.starter.vo.config.ConfigInfo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 配置信息
 *
 * @author zhangpba
 * @date 2022-06-21
 */
@Mapper
public interface ConfigInfoMapper {
    // 插入配置信息
    int addConfigInfo(ConfigInfo configInfo);

    // 根据条件查询所有符合条件的配置信息
    List<ConfigInfo> queryConfigInfos(ConfigInfo configInfo);

    // 根据条件查询所有符合条件的配置信息
    ConfigInfo queryConfigInfo(ConfigInfo configInfo);
}
