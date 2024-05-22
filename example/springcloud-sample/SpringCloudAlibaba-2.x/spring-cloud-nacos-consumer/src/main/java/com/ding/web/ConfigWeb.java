package com.ding.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tintin
 * @version V1.0
 * @Description 配置中心读取文件
 * @@copyright
 * @ClassName ConfigWeb
 * @date 2022-01-14 18:01
 */
//提供分布式的配置动态刷新
@RefreshScope
@RestController
@RequestMapping("/config")
@Api(tags = "nacos config")
public class ConfigWeb {
    private Logger log = LoggerFactory.getLogger(ConfigWeb.class);
    @Value( "${nacos.properties.age}" )
    private String age;

    /**
     * 对外提供的服务 HTTP接口
     * @param name
     * @return
     */
    @GetMapping("/hello")
    @ApiOperation(value = "获取nacos配置")
    public String hello(@RequestParam String name) {
        log.info("invoked name = " + name+ " age = " + age);
        return "hello " + name + " age = " + age;
    }
}
