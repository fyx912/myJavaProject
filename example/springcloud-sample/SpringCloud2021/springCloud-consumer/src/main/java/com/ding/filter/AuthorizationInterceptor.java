package com.ding.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ding
 * @create 2022826 0:08
 * @description Authorization拦截器
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)){
            logger.info("token verification result success!");
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        String method = request.getMethod();
        String url = request.getRequestURI();
        //没有权限
        response.setStatus(HttpStatus.UNAUTHORIZED.value());;
        response.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1000);
        jsonObject.put("msg","failed");
        jsonObject.put("reason","token无效！");
        logger.warn("token verification result failed!");
        logger.warn("request host:{},port:{},rul:{}  method:{}",request.getRemoteHost(),request.getRemoteAddr(),url,method);
        response.getWriter().write(jsonObject.toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
