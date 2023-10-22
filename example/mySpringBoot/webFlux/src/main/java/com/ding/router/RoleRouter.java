package com.ding.router;

import com.ding.handler.RoleHandler;
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
public class RoleRouter {

    @Bean
    public RouterFunction<ServerResponse> roleHandlerRouter(RoleHandler roleHandler){
        return RouterFunctions.nest(
                RequestPredicates.path("/role"),
                RouterFunctions.route(RequestPredicates.POST(""),roleHandler::addRole)
                        .andRoute(RequestPredicates.DELETE("/{id}"), roleHandler::delRole)
                        .andRoute(RequestPredicates.PUT(""), roleHandler::updateRole)
                        .andRoute(RequestPredicates.GET(""),roleHandler::getAllRole)
                        .andRoute(RequestPredicates.GET("/stream"), roleHandler::getAllRoleStream)
        );
    }
}
