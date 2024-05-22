package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tintin
 * @version V1.0
 * @Description dubbo 提供者
 * @@copyright
 * @ClassName DubboConsumerApp
 * @date 2022-01-25 15:04
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DubboProductApp {

    public static void main(String[] args){
        SpringApplication.run(DubboProductApp.class,args);
    }
}
