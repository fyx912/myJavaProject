package com.ding.common.config;

import com.ding.common.interceptor.AuthenticationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ding
 * @create 28 3:12
 * @description
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Resource
//    private LogInterceptor logInterceptor;
    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/register")
//                .excludePathPatterns("/swagger-ui/**","/v3/api-docs**","/swagger-resources/**");
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
