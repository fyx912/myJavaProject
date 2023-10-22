package com.ding;

import com.ding.filter.ServerGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName GatewayApp
 * @date 2022-01-14 10:36
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApp {

    public static void  main(String[] args){
        SpringApplication.run(GatewayApp.class);
    }

    /**
     * 配置gateway 自定义路由
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return  builder.routes()
                .route("jd_route",r->r.path("/jd").uri("http://jd.com:80/"))
                .route(
                        "provider_route",r -> r.path("/provider/**")
                                .filters(f -> f.stripPrefix(1).filters(new ServerGatewayFilter()))
                                .uri("lb://service-provider")
                )
                .route(
                        "dubbo-consumer_route",r -> r.path("/dubbo/**")
                                .filters(f -> f.stripPrefix(1).filters(new ServerGatewayFilter()))
                                .uri("lb://service-dubbo-consumer")
                )
                .build();
    }
}
