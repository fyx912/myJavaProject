package com.ding.boots.web;

import com.ding.common.utils.json.ApiResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ding
 * @create 27 2:11
 * @description
 */
@Tag(name = "index",description = "index")
@RestController
public class IndexWeb {

    @GetMapping("/")
    public ApiResult index(){
        return ApiResult.success("welcome to use BootAll......");
    }
    @GetMapping("failed")
    public ApiResult failed(){
        return ApiResult.failed();
    }

    @GetMapping("failedCause")
    public ApiResult failedToCause(){
        return ApiResult.failed("param is not null!");
    }

    @GetMapping("/time")
    public String time() {
        return "当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    @GetMapping("/value")
    public Integer value() {
        System.out.println(1 / 0);
        return Integer.MAX_VALUE;
    }
}
