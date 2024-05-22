package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tintin
 * @version V1.0
 * @Description  服务消费者
 * @@copyright
 * @ClassName ConsumerApp
 * @date 2022-01-13 17:06
 */
@RefreshScope  //config自动刷新配置
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApp {
    public static void main(String[] args){
        SpringApplication.run(ConsumerApp.class);
    }
}
