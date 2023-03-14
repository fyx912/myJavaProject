package com.ding;

import com.ding.config.MinioClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author ding
 * @create $YEAR-$MONTH-$DAY $TIME
 * @description
 */
@EnableConfigurationProperties({MinioClientConfig.class})
@SpringBootApplication
public class MinIOApp {
    public static void main(String[] args) {
        SpringApplication.run(MinIOApp.class,args);
    }
}