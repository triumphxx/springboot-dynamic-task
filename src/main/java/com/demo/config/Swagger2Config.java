package com.demo.config;

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
 * @author:wangyupeng
 * @Date:2020/3/30
 * @Time:10:10 下午
 * @desc:
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 通过createRestApi函数来构建一个DocketBean
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful API")
                .description("更多Spring Boot相关demo 请关注：https://gitee.com/wuwei1024")
                .termsOfServiceUrl("https://gitee.com/wuwei1024")
                .contact(new Contact("wuwei", "https://gitee.com/wuwei1024", "1472101076@qq.com"))
                .version("1.0")
                .build();
    }
}
