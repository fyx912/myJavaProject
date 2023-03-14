package com.ding.boots.web;

import com.ding.boots.dto.RegisterDTO;
import com.ding.boots.dto.SignInDTO;
import com.ding.boots.service.SignService;
import com.ding.common.utils.JWTUtils;
import com.ding.common.utils.json.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * @author ding
 * @create 27 17:49
 * @description
 */
@Tag(name = "SignWeb",description ="登录" )
@RestController
public class SignWeb {
    private Logger logger = LoggerFactory.getLogger(SignWeb.class);
    @Resource
    private SignService signService;

    @PostMapping(value = "login",produces = "application/json;charset=UTF-8")
    @Operation(summary = "登录接口",description = "",
            parameters = {@Parameter(name = "signInDTO",description = "")})
    public ApiResult login(@RequestBody  @Valid SignInDTO signInDTO, HttpServletResponse response){
        Map<String, Object> map = signService.signIn(signInDTO);
        response.setHeader(JWTUtils.AUTHORIZATION, (String) map.get("token"));
        return ApiResult.success(map.get("account"));
    }
    @Operation(summary = "注销接口",description = "注销接口")
    @PostMapping(value = "signOut",produces = "application/json;charset=UTF-8")
    public ApiResult signOut(){
//        logger.info("login success. token:{}",token);
        return ApiResult.success();
    }
    @Operation(summary = "注册接口")
    @PostMapping(value = "register",produces = "application/json;charset=UTF-8")
    public ApiResult register(@RequestBody @Valid RegisterDTO registerDTO){
        return signService.register(registerDTO);
    }
}
