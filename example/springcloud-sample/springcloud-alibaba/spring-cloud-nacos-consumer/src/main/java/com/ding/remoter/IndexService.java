package com.ding.remoter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tintin
 * @version V1.0
 * @Description feign远程调用，服务降级
 * @@copyright
 * @ClassName IndexServer
 * @date 2022-01-13 17:58
 */
@FeignClient(value = "service-provider",fallback = IndexServiceFallback.class)
public interface IndexService {

    @GetMapping(value = "/echo/{str}")
    String echo(@PathVariable("str") String str);
}
