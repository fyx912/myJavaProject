package com.ding;

import com.ding.filter.ServerGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

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
                        "service-consumer-route",r -> r.path("/service-consumer/**")
                                .filters(f -> f.stripPrefix(1).filters(new ServerGatewayFilter()))
                                .uri("lb://service-consumer")
                )
//                .route(
//                        "dubbo-consumer_route",r -> r.path("/dubbo/**")
//                                .filters(f -> f.stripPrefix(1).filters(new ServerGatewayFilter()))
//                                .uri("lb://service-dubbo-consumer")
//                )
                .build();
    }
}
