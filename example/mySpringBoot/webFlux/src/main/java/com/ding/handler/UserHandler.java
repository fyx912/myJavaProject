package com.ding.handler;

import com.ding.doman.User;
import com.ding.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <p> user Handle </p>
 *
 * @author Tintin
 * @date 2023-10-21 23:25:38
 **/
@Component
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> addUser(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.saveAll(request.bodyToMono(User.class)),User.class);
    }

    public Mono<ServerResponse> delUser(ServerRequest request) {
        return userRepository.findById(Integer.parseInt(request.pathVariable("id")))
                .flatMap(user -> userRepository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Transactional
    public Mono<ServerResponse> updateUser(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.saveAll(request.bodyToMono(User.class)), User.class);
    }
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
//        return userRepository.findAll().collectList().flatMap(users ->
//            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
//                    .bodyValue(JSONUtil.toJsonStr(ApiResponse.success(users))));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> getAllUserStream(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(userRepository.findAll(), User.class);
    }

}
