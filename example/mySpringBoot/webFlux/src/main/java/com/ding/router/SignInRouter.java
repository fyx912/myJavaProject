package com.ding.router;

import com.ding.handler.SignHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


/**
 * <p> signIn路由 </p>
 *
 * @author Tintin
 * @date 2023-10-22 16:11:57
 **/
@Component
public class SignInRouter {

    @Bean
    public RouterFunction<ServerResponse> signHandlerRouter(SignHandler signHandler){
        return RouterFunctions.route(RequestPredicates.POST("signIn"),signHandler::signIn);
    }
}
