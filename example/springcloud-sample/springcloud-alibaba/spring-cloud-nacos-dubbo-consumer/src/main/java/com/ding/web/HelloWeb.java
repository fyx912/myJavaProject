package com.ding.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ding.api.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName HelloWeb
 * @date 2022-01-25 15:17
 */
@RestController
@RequestMapping("hello")
@Api("helloDubbo")
public class HelloWeb {
    private Logger log = LoggerFactory.getLogger(HelloWeb.class);
    @DubboReference
    HelloService helloService;

    @GetMapping
    @SentinelResource(value = "hello",blockHandler = "helloBlockHandler",fallback = "helloFallback")
    @ApiOperation(value ="dubbo sentinel 熔断")
    public String hello() {
        return helloService.hello("dubbo.com");
    }

    //只负责sentinel控制台配置违规
    //sentinel定义的失败调用或限制调用，若本次访问被限流或服务降级，则调用blockHandler指定的接口。
    public String helloBlockHandler(BlockException blockException) {
        log.warn("Sentinel--blockHandler--");
        return "Sentinel--blockHandler--";
    }
    //fallback:只负责业务异常
    public String helloFallback(Throwable e) {
        log.warn("Sentinel--fallback--限流.....");
        return "Sentinel--fallback--限流.....";
    }
}
