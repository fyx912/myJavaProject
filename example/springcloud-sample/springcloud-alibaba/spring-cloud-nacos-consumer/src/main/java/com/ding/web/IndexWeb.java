package com.ding.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ding.remoter.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName IndexWeb
 * @date 2022-01-13 17:12
 */
@RestController
@Api(tags = "consumer服务")
public class IndexWeb {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ServiceInstance serviceInstance;

    @Resource
    private IndexService indexService;

    @GetMapping(value = "/echo/{name}")
    @ApiOperation(value = "获取nacos信息",notes = "nacos信息",httpMethod = "GET")
    public String echo(@PathVariable String name) {
        String msg = "Hi "+name;
        msg += ", \n " + "my ip address  : "+serviceInstance.getHost()+",port:" + serviceInstance.getPort();;
        msg += ",\n  serverName:【consumer】,services  list : "+discoveryClient.getServices();
        return msg;
    }

    @GetMapping (value = "/echo-rest/{str}")
    @ApiOperation(value = "Feign远程调用",notes = "Feign远程调用",httpMethod = "GET")
    public String rest(@PathVariable String str) {
        return indexService.echo(str);
    }


    @GetMapping (value = "hello")
    @ApiOperation(value = "sentinel限流",notes = "sentinel限流",httpMethod = "GET")
    @SentinelResource(value = "helloBlock",blockHandler ="helloBlockHandler")//sentinel限流
    public String hello(ServletRequest request) {
        return "hello world! port:" +request.getRemotePort();
    }

    public String helloBlockHandler(BlockException exception){ //限流
        return  "...限流了... ";
    }

    @GetMapping
    public String index(){
        return " welcome to spring cloud !";
    }

    @PostMapping
    @ApiOperation(value = "post 方法",notes = "post 方法",httpMethod = "POST")
    public String post(){
        return " post method!";
    }

    @DeleteMapping
    @ApiOperation(value = "delete 方法",notes = "delete 方法")
    public String delete(){
        return " delete method!";
    }
    @PutMapping
    @ApiOperation(value = "update 方法",notes = "update 方法",httpMethod = "PUT")
    public String update(){
        return " update method!";
    }
}


