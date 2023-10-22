package com.ding.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestWeb {
    private Logger logger = LoggerFactory.getLogger(TestWeb.class);
    @GetMapping()
    public String index(){
        return "Hi, I'm  Provider....";
    }

    @GetMapping("echo/{name}")
    public String echo(@PathVariable("name") String name){
        logger.info("echo method param :{}",name);
        return "Hi! "+name+ ", I'm Provider....";
    }
}
