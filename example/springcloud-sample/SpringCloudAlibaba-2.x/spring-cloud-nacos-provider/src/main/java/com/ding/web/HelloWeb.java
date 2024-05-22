package com.ding.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName HelloWeb
 * @date 2022-01-13 17:10
 */
@RefreshScope
@RestController
@RequestMapping("/hello")
public class HelloWeb {
    @Value("${user.name}")
    private String name;

    @GetMapping(value = "")
    public String index(){
        return  name;
    }
}
