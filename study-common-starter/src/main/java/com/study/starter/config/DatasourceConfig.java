package com.study.starter.config;

import com.study.starter.properties.DatasourceProperties;
import com.study.starter.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类-模拟数据源
 *
 * @author zhangpba
 * @date 2022-04-11
 */
@Configuration
@EnableConfigurationProperties(DatasourceProperties.class)
@ConditionalOnProperty(prefix = "datasource", name = "datasourceName", havingValue = "true")
public class DatasourceConfig {

    @Autowired
    private DatasourceProperties datasourceProperties;

    @Bean(name = "datasourceService")
    public DatasourceService datasourceService() {
        DatasourceService datasourceService = new DatasourceService();
        datasourceService.setDriver(datasourceProperties.getDriver());
        datasourceService.setPassword(datasourceProperties.getPassword());
        datasourceService.setUrl(datasourceProperties.getUrl());
        datasourceService.setUsername(datasourceProperties.getUsername());
        return datasourceService;
    }

}
