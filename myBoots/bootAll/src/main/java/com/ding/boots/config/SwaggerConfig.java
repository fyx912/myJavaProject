//package com.ding.boots.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.ArrayList;
//
///**
// *  http://localhost:8080/doc.html
// *  http://localhost:8080/swagger-ui/index.html
// * @author ding
// * @create 28 16:30
// * @description
// */
//@Configuration
//@EnableOpenApi
//public class SwaggerConfig {
//    /**
//     * 用于读取配置文件 application.properties 中 swagger 属性是否开启
//     */
//    @Value("${swagger.enabled}")
//    private Boolean swaggerEnabled=false;
//
//    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                // 是否开启swagger
//                .enable(swaggerEnabled)
//                .select()
//                // 过滤条件，扫描指定路径下的文件
//                .apis(RequestHandlerSelectors.basePackage("com.ding.boots.web"))
//                // 指定路径处理，PathSelectors.any()代表不过滤任何路径
//                .paths(PathSelectors.any())
//                .build()
////                .groupName("desertsGroup") //分组
//                ;
//    }
//
//    private ApiInfo apiInfo() {
//        /*作者信息*/
//        Contact contact = new Contact("tintin", "https://fyx912.github.io", "fyx912@qq.com");
//        return new ApiInfo(
//                "Spring Boot 集成 Swagger3 ",
//                "Spring Boot 集成 Swagger3 接口文档",
//                "v1.0",
//                "https://fyx912.github.io",
//                contact,
//                "Apache 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0",
//                new ArrayList()
//        );
//    }
//}
