package com.ding.handler;

import com.ding.doman.sys.Role;
import com.ding.repository.RoleRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <p> 角色handler </p>
 *
 * @author Tintin
 * @date 2023-10-22 00:59:31
 **/
@Component
public class RoleHandler {
    private final RoleRepository roleRepository;

    public RoleHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Mono<ServerResponse> addRole(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleRepository.saveAll(request.bodyToMono(Role.class)),Role.class);
    }

    public Mono<ServerResponse> delRole(ServerRequest request) {
        return roleRepository.findById(Integer.parseInt(request.pathVariable("srId")))
                .flatMap(Role -> roleRepository.delete(Role).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateRole(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleRepository.saveAll(request.bodyToMono(Role.class)), Role.class);
    }
    public Mono<ServerResponse> getAllRole(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleRepository.findAll(), Role.class);
    }

    public Mono<ServerResponse> getAllRoleStream(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(roleRepository.findAll(), Role.class);
    }
}
