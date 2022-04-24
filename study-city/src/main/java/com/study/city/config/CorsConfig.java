package com.study.city.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域问题
 *
 * @author zhangpba
 * @date 2022-04-18
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        // 添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1) 允许的域,不要写*，否则cookie就无法使用了
        corsConfiguration.addAllowedOrigin("*");
        // 2) 是否发送Cookie信息
        corsConfiguration.setAllowCredentials(true);
        // 3) 允许的请求方式GET POST等
        corsConfiguration.addAllowedMethod("*");
        // 4）允许的头信息
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);

        return corsConfiguration;
    }
}
