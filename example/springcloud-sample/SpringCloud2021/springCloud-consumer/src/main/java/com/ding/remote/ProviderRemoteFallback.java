package com.ding.remote;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProviderRemoteFallback implements ProviderRemote{
    private Logger log = LoggerFactory.getLogger(ProviderRemoteFallback.class);
    @Override
    public String echo(String str) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",-1);
        jsonObject.put("msg","feign-sentinel熔断,当时接口暂不可用!");
        log.warn("feign sentinel 熔断,data:{}",jsonObject);
        return jsonObject.toJSONString();
    }

    @Override
    public String uploadFile(MultipartFile file) {
        return responseData();
    }

    @Override
    public String hello() {
        return responseData();
    }

    private String responseData(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",-1);
        jsonObject.put("msg","feign-sentinel熔断,当时接口暂不可用!");
        log.warn("feign sentinel 熔断,data:{}",jsonObject);
        return jsonObject.toJSONString();
    }
}
