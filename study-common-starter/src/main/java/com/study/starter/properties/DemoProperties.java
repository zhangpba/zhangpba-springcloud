package com.study.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类-demo
 *
 * @author zhangpba
 * @date 2022-04-11
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String sayWhat;

    private String toWho;

    public String getSayWhat() {
        return sayWhat;
    }

    public void setSayWhat(String sayWhat) {
        this.sayWhat = sayWhat;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }
}
