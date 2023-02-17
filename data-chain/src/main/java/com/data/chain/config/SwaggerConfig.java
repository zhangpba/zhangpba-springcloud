package com.data.chain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 *
 * @author zhangpba
 * @date 2021-09-26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**")).build();
    }

    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.data.chain.admin.controller"))
                .paths(PathSelectors.ant("/**")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数据链接口 文档") // 文档标题
                .description("This is a restful api document of File for '数据链接口 文档'") // 文档描述
                .version("1.0") // 版本
                .contact(new Contact("zhangpba","","peilizhu@tencent.com"))// 作者及联系方式
                .build();
    }
}