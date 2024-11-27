package com.ding.provider.boot;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelleApiImpl implements HelleApi{
    @Override
    public String test() {
        return "test impl";
    }
}
