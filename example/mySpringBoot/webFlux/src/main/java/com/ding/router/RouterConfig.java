package com.ding.router;

import com.ding.handler.TimeHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName RouterConfig
 * @date 2020-12-03 18:42
 */
@Component
public class RouterConfig {
    @Resource
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> myRouter(){
        return route(GET("/time"),request -> timeHandler.getTime(request))
                .andRoute(GET("date"),request -> timeHandler.getDate(request))
                .andRoute(GET(""), request -> ok().body(Mono.just("Welcome flux..."),String.class));
    }

//    @Bean
//    public RouterFunction<ServerResponse> weChatRouter(WeChatHandler weChatHandler){
//        String prentPath = "wechat";
//        return route(POST(prentPath+"/officialAccount/bind"),weChatHandler::weChatOfficialAccountBind);
//    }
}
