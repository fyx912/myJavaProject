package com.ding.router;

import com.ding.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <p> user路由 </p>
 * @author tintin
 * @date 2023-10-21 23:23-11
 **/
@Component
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userHandlerRouter(UserHandler userHandler){
        return RouterFunctions.nest(
                RequestPredicates.path("/user"),
                RouterFunctions.route(RequestPredicates.POST(""),userHandler::addUser)
                        .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::delUser)
                        .andRoute(RequestPredicates.PUT(""), userHandler::updateUser)
                        .andRoute(RequestPredicates.GET(""),userHandler::getAllUser)
                        .andRoute(RequestPredicates.GET("/stream"), userHandler::getAllUserStream)
        );
    }
}
