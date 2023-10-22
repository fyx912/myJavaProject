package com.ding.web;

import com.ding.remote.ProviderRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TestWeb {
    private Logger logger = LoggerFactory.getLogger(TestWeb.class);
    @Resource
    private ProviderRemote remote;
    @GetMapping()
    public String index(){
        return "Hi, I'm  Consumer....";
    }

    @GetMapping("hello")
    public String hello(){
        return remote.hello();
    }

    @GetMapping("echo/{name}")
    public String remoteEcho(@PathVariable("name") String name, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        logger.info("Authorization param :{}",authorization);
        logger.info("echo method param :{}",name);
        String revert = remote.echo(name);
        return revert;
    }
}
