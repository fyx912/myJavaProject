package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobApp {

    public static void main(String[] args){
        System.setProperty("zookeeper.sasl.client", "false");
        SpringApplication.run(JobApp.class,args);
    }
}
