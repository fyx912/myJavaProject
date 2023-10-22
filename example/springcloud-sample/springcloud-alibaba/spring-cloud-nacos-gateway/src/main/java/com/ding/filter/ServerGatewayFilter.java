package com.ding.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author tintin
 * @version V1.0
 * @Description  自定义过滤器
 * @@copyright
 * @ClassName ServerGatewayFilter
 * @date 2022-01-14 15:57
 */
public class ServerGatewayFilter implements GatewayFilter, Ordered {
    private Logger log = LoggerFactory.getLogger(ServerGatewayFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("...ServerGatewayFilter...");
        return chain.filter( exchange );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
