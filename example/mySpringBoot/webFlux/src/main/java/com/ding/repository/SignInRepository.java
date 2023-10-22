package com.ding.repository;

import com.ding.doman.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-10-22 15:20:26
 **/
public interface SignInRepository extends ReactiveCrudRepository<User,Integer> {

    @Query(" select * from u_user where account = :account or phone_number = :account limit 1 ")
    Mono<User> signIn(String account);
}
