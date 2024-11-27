package com.ding.business.provider.api.Impl;

import com.ding.provider.api.IndexApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApiImpl implements IndexApi {
    @Override
    public String index() {
        return "Welcome to Business provider";
    }

    @Override
    public String hello() {
        return "hello,I`m  Business provider!";
    }
}
