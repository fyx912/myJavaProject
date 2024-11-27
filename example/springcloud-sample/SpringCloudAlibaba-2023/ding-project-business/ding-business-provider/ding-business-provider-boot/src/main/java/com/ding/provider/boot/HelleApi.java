package com.ding.provider.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "h")
public interface HelleApi {
    @GetMapping(value = "test")
    String test();
}
