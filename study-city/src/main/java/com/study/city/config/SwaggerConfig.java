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
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API 文档") // 文档标题
                .description("This is a restful api document of File.") // 文档描述
                .version("1.0") // 版本
                .contact(new Contact("zhangpba","","zhangpba@qq.com"))// 作者及联系方式
                .build();
    }
}
