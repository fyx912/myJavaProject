package com.ding.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ding.doman.User;
import com.ding.utils.ApiResponse;
import com.ding.dto.SignInDTO;
import com.ding.repository.SignInRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-10-22 14:43:57
 **/
@Component
public class SignHandler {
    private static final Logger logger = LoggerFactory.getLogger(SignHandler.class);

    private final SignInRepository signInRepository;

    public SignHandler(SignInRepository signInRepository) {
        this.signInRepository = signInRepository;
    }

    public Mono<ServerResponse> signIn(ServerRequest request){
        Mono<SignInDTO> signInDTOMono = request.bodyToMono(SignInDTO.class);
        return signInDTOMono.flatMap(signInDTO -> {
            logger.info(" signIn data :{}", JSONUtil.toJsonStr(signInDTO));

            String account = signInDTO.getAccount();
            String passwd = signInDTO.getPasswd();
            Assert.notBlank(account,"账号不能为空");
            Assert.notBlank(passwd,"密码不能为空");

            Mono<User> userMono =  signInRepository.signIn(account);

            return userMono.flatMap(user -> {
                if (user.getPasswd().equals(passwd)) {
                    // Successful login
                    return ServerResponse.ok().bodyValue(ApiResponse.authorization(UUID.randomUUID().toString()));
                } else {
                    // Invalid credentials
                    return ServerResponse.status(HttpStatus.UNAUTHORIZED).bodyValue(JSONUtil.toJsonStr(ApiResponse.failed()));
                }
            }).switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED).bodyValue(JSONUtil.toJsonStr(ApiResponse.failed())));
        });
    }
    private void accountIsLock(String account){

    }

}
