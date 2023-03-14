package com.ding.boots.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ding
 * @create 20230211 1:36
 * @description SpringDoc 配置类
 */
@Configuration
public class SpringDocConfig {
    /**
     * SpringDoc 标题、描述、版本等信息配置
     *
     * @return openApi 配置信息
     */
    @Bean
    public OpenAPI springDocOpenAPI() {
        return new OpenAPI().info(new Info()
                        .title("fyx912 API")
                        .description("fyx912接口文档说明")
                        .version("v0.0.1-SNAPSHOT")
                        .license(new License().name("fyx912项目博客专栏")
                                .url("https://blog.csdn.net/weihao0240/category_12166012.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("github项目地址")
                        .url("https://github.com/"))
                // 配置Authorizations
                .components(new Components().addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }

    /**
     * 默认接口 分组
     * @return 默认分组接口
     */
    @Bean
    public GroupedOpenApi siteDefaultApi() {
        return GroupedOpenApi.builder()
                .group("默认接口")
                .packagesToScan("com.ding.boots.web")
                .build();
    }

    /**
     * sys 分组
     * @return sys分组接口
     */
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("sys接口").packagesToScan("com.ding.boots.web.sys")
//                .pathsToMatch("/sys/**")
                .build();
    }


}
