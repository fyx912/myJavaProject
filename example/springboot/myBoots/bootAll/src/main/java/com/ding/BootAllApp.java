package com.ding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName BootAllApp
 * @date 2020-03-09 12:17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ding.boots","com.ding.common"})
public class BootAllApp {
    public static void main(String[] args){
        SpringApplication.run(BootAllApp.class,args);
    }

}
