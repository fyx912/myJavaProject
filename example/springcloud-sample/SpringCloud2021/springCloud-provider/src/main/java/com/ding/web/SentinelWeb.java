package com.ding.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SentinelWeb {

    private Logger logger = LoggerFactory.getLogger(SentinelWeb.class);

    @SentinelResource(value = "hello",blockHandler = "sentinelFallback")
    @GetMapping("hello")
    public String hello(){
        return "test";
    }
    @SentinelResource(value = "say",blockHandler = "sentinelFallback")
    @GetMapping("say")
    public String say(){
        return "test";
    }

    @GetMapping("getA")
    @SentinelResource(value = "getA",blockHandler = "sentinelFallback")
    public String testA(){
        return "--------testA";
    }

    @GetMapping("getB")
    @SentinelResource(value = "getB",blockHandler = "sentinelFallback")
    public String testB(){
        return "--------testB";
    }

    public String sentinelFallback(BlockException e){
        e.printStackTrace();
        HttpServletRequest request = getRequest();
        String msg = request.getRequestURI()+"被限流";
        logger.warn(msg);
        return msg;
    }

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.cast(RequestContextHolder.currentRequestAttributes());
        return requestAttributes.getRequest();
    }
}
