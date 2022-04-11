package com.study.starter.service;

/**
 * 配置类-demo
 *
 * @author zhangpba
 * @date 2022-04-11
 */
public class DemoService {

    public String sayWhat;

    public String toWho;

    public DemoService(String sayWhat, String toWho) {
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }

    public String say() {
        return this.sayWhat + "!" + toWho;
    }
}
