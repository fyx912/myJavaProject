package com.ding.repository;

import com.ding.doman.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-10-22 00:01:01
 **/
public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
    Flux<User> findByNameLike(String name);


//    Mono<User> signIn(String account);

}
