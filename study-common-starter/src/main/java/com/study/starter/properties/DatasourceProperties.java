package com.study.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类-模拟数据源
 *
 * @author zhangpba
 * @date 2022-04-11
 */
@ConfigurationProperties(prefix = "datasource")
public class DatasourceProperties {

    private String url;

    private String driver;

    private String username;

    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
