package com.ding.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestWeb {
    @GetMapping()
    public String index(){
        return "Hi, I'm  Gateway....";
    }
}
