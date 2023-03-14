package com.ding.common.interceptor;

import com.ding.common.exception.ServiceException;
import com.ding.common.utils.JWTUtils;
import com.ding.common.utils.json.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.UUID;

/**
 * MDC日志记录
 * @Auther: ding
 * @Date: 2020-01-03 15:47
 * @Description:
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    private final static String REQUEST_ID = "requestId";
    private static final Logger logger  = LoggerFactory.getLogger(LogInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JWTUtils.AUTHORIZATION);
        //token不存在
        if (token == null || token.equals("")) throw new ServiceException(ApiResult.failed("请先登录"));
        //验证token
        String sub = JWTUtils.validateToken(token);
        if (sub == null || sub.equals(""))
            throw new ServiceException(ApiResult.failed("token验证失败"));

        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)){
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.AUTHORIZATION,newToken);
        }
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        String remoteIp = request.getRemoteAddr();
        String uuid = UUID.randomUUID().toString();
        logger.info("put requestId ({}) to logger",uuid);
        logger.info("request id:{}, client ip:{}, X-Forwarded-For:{}",uuid,remoteIp,xForwardedForHeader);
        MDC.put(REQUEST_ID,uuid);
        MDC.put("remoteIp",remoteIp);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uuid = MDC.get(REQUEST_ID);
        logger.info("remove requestId ({}) from logger", uuid);
        MDC.remove(REQUEST_ID);
    }
}
