package com.ding.common.interceptor;

import com.ding.common.config.PassToken;
import com.ding.common.config.UserLoginToken;
import com.ding.common.exception.ServiceException;
import com.ding.common.utils.JWTUtils;
import com.ding.common.utils.json.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Method;

/**
 * @author ding
 * @create 28 17:47
 * @description
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object ) throws Exception {
        String token = request.getHeader(JWTUtils.AUTHORIZATION);// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                //token不存在
                if (token == null || token.equals(""))
                    throw new ServiceException(ApiResult.failed("请先登录"));
                //验证token
                String sub = JWTUtils.validateToken(token);
                if (sub == null || sub.equals(""))
                    throw new ServiceException(ApiResult.failed("token验证失败"));

                //更新token有效时间 (如果需要更新其实就是产生一个新的token)
                if (JWTUtils.isNeedUpdate(token)){
                    String newToken = JWTUtils.createToken(sub);
                    response.setHeader(JWTUtils.AUTHORIZATION,newToken);
                }
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
