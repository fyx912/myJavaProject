package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName ProviderApp
 * @date 2022-01-13 17:24
 */
@RefreshScope  //自动刷新配置
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApp {
    public static void main(String[] args){
        SpringApplication.run(ProviderApp.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
