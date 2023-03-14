package com.ding.common.utils.json;

import com.alibaba.fastjson2.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ding
 * @create 27 4:09
 * @description 拦截Controller方法的返回对象，统一处理返回值/响应实体
 */
@RestControllerAdvice(basePackages = {"com.ding.boots.web"})
public class ApiResultWrapper implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (object instanceof String) {
            return JSON.toJSONString(ApiResult.success(object));
        }
        //这个判断的作用：防止全局异常处理后返回的结果（类型为ApiResult）再次被包装
        if (object instanceof ApiResult) {
            return object;
        }
        return ApiResult.success(object);
    }
}
