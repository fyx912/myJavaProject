package com.ding.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName IndexWeb
 * @date 2022-01-13 17:12
 */
@RestController
public class IndexWeb {
    private Logger logger = LoggerFactory.getLogger(IndexWeb.class);
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ServiceInstance serviceInstance;

    @GetMapping(value = "/echo/{name}")
    public String echo(@PathVariable String name) {
        String msg = "Hi "+name;
        msg += ", \n " + "my ip address  : "+serviceInstance.getHost()+",port:" + serviceInstance.getPort();
        msg += ",\n  serverName:【provider】,services  list : "+discoveryClient.getServices();
        return msg;
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping (value = "/echo-rest/{str}")
    public String rest(@PathVariable String str) {
        logger.info(" robbin....");
        return restTemplate.getForObject("http://service-consumer/echo/" + str, String.class);
    }

    @GetMapping
    public String index(){
        return " welcome to spring cloud !";
    }
}
