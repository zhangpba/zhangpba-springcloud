package com.study.city.config;

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
 * @date 2022-04-12
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("考试呗整体接口")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**")).build();
    }

    @Bean
    public Docket dataApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("数据管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.city.data.controller"))
                .paths(PathSelectors.ant("/**")).build();
    }

    @Bean
    public Docket examApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("考试管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.city.exam.controller"))
                .paths(PathSelectors.ant("/**")).build();
    }

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.city.user.controller"))
                .paths(PathSelectors.ant("/**")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("城市服务接口 文档") // 文档标题
                .description("This is a restful api document of File for '城市服务接口 文档'") // 文档描述
                .version("1.0") // 版本
                .contact(new Contact("zhangpba", "", "zhangpba@qq.com"))// 作者及联系方式
                .build();
    }
}
