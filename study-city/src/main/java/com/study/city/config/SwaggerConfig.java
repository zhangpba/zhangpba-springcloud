package com.study.city.config;

import com.study.city.utils.TokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(getParameterBuilder());// 给header中设置参数
    }

    @Bean
    public Docket dataApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("数据管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.city.data.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(getParameterBuilder());// 给header中设置参数
    }

    @Bean
    public Docket examApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("考试管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.city.exam.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(getParameterBuilder());// 给header中设置参数
    }

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.city.user.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(getParameterBuilder());// 给header中设置参数
    }

    @Bean
    public Docket dictApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公共字典管理")
                .apiInfo(apiInfo())
                .enable(true)//是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.city.common.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(getParameterBuilder());// 给header中设置参数
    }

    /**
     * 文档的整体介绍信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("城市服务接口 文档") // 文档标题
                .description("This is a restful api document of File for '城市服务接口 文档'") // 文档描述
                .version("1.0") // 版本
                .contact(new Contact("zhangpba", "", "zhangpba@qq.com"))// 作者及联系方式
                .build();
    }

    /**
     * header中设置参数
     *
     * @return
     */
    private List<Parameter> getParameterBuilder() {
        List<Parameter> pars = new ArrayList<Parameter>();

        // header中的token参数非必填，传空也可以
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(TokenUtils.TOKEN_NAME).description("user token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();

        // 根据每个方法名也知道当前方法在设置什么参数
        pars.add(tokenPar.build());
        return pars;
    }

}
