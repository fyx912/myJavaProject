package com.ding.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ding.common.exception.ServiceException;
import com.ding.common.utils.json.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）,默认：{"alg": "HS512","typ": "JWT"}
 * payload的格式 设置：（用户信息、创建时间、生成时间）
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * @author ding
 * @create 28 2:10
 * @description
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtils {


    //定义token返回头部
    public static String header;

    //token前缀
    public static String tokenPrefix="Bearer";

    //签名密钥
    public static String secret="tintin!@qweasd";

    //有效期
    public static Integer expireTime=2;

    //存进客户端的token的key名 authorization
    public static final String  AUTHORIZATION= "Authorization";

    public static String getHeader() {
        return header;
    }

    public static void setHeader(String header) {
        JWTUtils.header = header;
    }

    public static String getTokenPrefix() {
        return tokenPrefix;
    }

    public static void setTokenPrefix(String tokenPrefix) {
        JWTUtils.tokenPrefix = tokenPrefix;
    }

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        JWTUtils.secret = secret;
    }
    /**
     * 创建TOKEN
     * @param sub
     * @return
     */
    public static String createToken(String sub){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,expireTime);
        return  JWT.create()
                .withSubject(sub)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC512(secret));
    }


    /**
     * 验证token
     * @param token
     */
    public static String validateToken(String token){
        try {
            return tokenPrefix + JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getSubject();
        } catch (TokenExpiredException e){
            e.printStackTrace();
            throw new ServiceException(ApiResult.failed("token已经过期"));
        } catch (Exception e){
            throw new ServiceException(ApiResult.failed("token验证失败"));
        }
    }

    /**
     * 检查token是否需要更新
     * @param token
     * @return
     */
    public static boolean isNeedUpdate(String token){
        //获取token过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e){
            return true;
        } catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(ApiResult.failed("token验证失败"));
        }
        //如果剩余过期时间少于过期时常的一般时 需要更新
        return (expiresAt.getTime()-System.currentTimeMillis()) < (expireTime>>1);
    }
}
