package com.ding;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;

/**
 * @author ding
 * @create 28 2:16
 * @description
 */
public class JWTTest extends BaseTest {
    @Test
    public void contextLoads() {
        //生产token
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,1000);

        String token = JWT.create()
                //设置载体
                .withClaim("username","zhangSan")
                .withClaim("userid",12)
                //token过期时间
                .withExpiresAt(instance.getTime())
                //签名
                .sign(Algorithm.HMAC256("123456"));
        System.out.println(token);
    }

    @Test
    public void contextLoads2() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
        //验证token
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjE2MjUzNjgsInVzZXJpZCI6MTIsInVzZXJuYW1lIjoiemhhbmdTYW4ifQ.HJBcpZrRL7SZgNCO_ZEJ_6tM7EDoW61Qi-HCi-_krJ8");
        verify.getSubject();
        //验证成功后取出载体
        System.out.println(verify.getClaims().get("username").toString());
        System.out.println(verify.getClaims().get("userid").asInt());
    }
}
