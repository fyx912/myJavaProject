package com.ding.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ding
 * @create 25 22:26
 * @description 自定义过滤器
 */
public class ServerGatewayFilter implements GatewayFilter, Ordered {
    private Logger logger = LoggerFactory.getLogger(ServerGatewayFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("...ServerGatewayFilter...");
        ServerHttpRequest request =  exchange.getRequest();
        String url = request.getPath().pathWithinApplication().value();
        logger.info("请求URL:{}",url);
        logger.info("请求method:{}",request.getMethod());

        //获取param 请求参数
//        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        //获取header
//        String userId = exchange.getRequest().getHeaders().getFirst("user-id");
//        logger.info("userId：" + userId);
//
//        if (StringUtils.isBlank(userId))
//        {
//            logger.info("*****头部验证不通过，请在头部输入  user-id");
//            //终止请求，直接回应
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }

        return chain.filter(exchange);
    }
    //   值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
