package com.ding.provider.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface IndexApi {
    @GetMapping
    String index();

    @GetMapping(value = "hello")
    String hello();
}
