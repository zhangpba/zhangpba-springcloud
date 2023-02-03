package com.study.city.config;

import com.study.city.interceptor.AuthenticationInterceptor;
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

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 访客的IP拦截
        registry.addInterceptor(visitorInterceptor)// 注册自定义拦截器
                .addPathPatterns("/weather/**")
                .addPathPatterns("/gold/**")
                .addPathPatterns("/area/**")    // 拦截的路径
                .addPathPatterns("/qq/**")
                .addPathPatterns("/jh-weather/**")
                .addPathPatterns("/remind/**")
                .addPathPatterns("/character/**")
                .addPathPatterns("/gdmj/**")
                .addPathPatterns("/lunar/**")
                .addPathPatterns("/one-day/**")
                .addPathPatterns("/pyq/**")
                .addPathPatterns("/quname/**")
                .addPathPatterns("/saylove/**")
                .addPathPatterns("/dict/**")
                .addPathPatterns("/config/**")
                .addPathPatterns("/sysUser/**")
                .excludePathPatterns("/swagger-ui.html"); // 不拦截的路径

        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns()
                .addPathPatterns("/**");
    }
}
