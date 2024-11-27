package com.ding.consumer.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerBootStarter.class,args);
    }
}
