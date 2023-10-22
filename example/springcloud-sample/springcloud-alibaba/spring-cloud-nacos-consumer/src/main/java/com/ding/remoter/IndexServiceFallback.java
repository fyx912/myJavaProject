package com.ding.remoter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tintin
 * @version V1.0
 * @Description   服务降级
 * @@copyright
 * @ClassName IndexServiceFallback
 * @date 2022-01-18 11:41
 */
@Component
public class IndexServiceFallback implements IndexService{
    private Logger log = LoggerFactory.getLogger(IndexServiceFallback.class);
    @Override
    public String echo(String str) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",-1);
        jsonObject.put("msg","feign-sentinel熔断,当时接口暂不可用!");
        log.warn("feign sentinel 熔断,data:{}",jsonObject);
        return jsonObject.toJSONString();
    }
}
