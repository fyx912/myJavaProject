package com.ding.api.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName HelloServiceImpl
 * @date 2022-01-25 15:11
 */
//dubbo 注解 服务启动的时候回扫描带dubboservice的注解 然后根据我们配置的协议转换成对应的协议头开头的请求地址
@DubboService(protocol = "dubbo")
public class HelloServiceImpl implements HelloService{
    private Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    @SentinelResource(value = "hello",blockHandler = "helloBlockHandler",fallback = "helloFallback")
    public String hello(String name) {
        log.info("dubbo produce.....arg:{}",name);
        return "hello " + name;
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
