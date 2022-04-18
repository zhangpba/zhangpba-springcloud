package com.study.city.config;

import com.study.city.interceptor.VisitorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类
 *
 * @author zhangpba
 * @2022-04-18
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private VisitorInterceptor visitorInterceptor;
    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(visitorInterceptor)// 注册自定义拦截器
                .addPathPatterns("/weather/**")
                .addPathPatterns("/gold/**")
                .addPathPatterns("/area/**")    // 拦截的路径
                .excludePathPatterns("/swagger-ui.html"); // 不拦截的路径
    }
}
