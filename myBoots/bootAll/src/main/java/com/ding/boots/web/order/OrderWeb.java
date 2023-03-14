package com.ding.boots.web.order;

import com.ding.common.utils.json.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ding
 * @create 27 17:59
 * @description
 */
@Tag(name ="OrderWeb",description = "订单")
@RestController
@RequestMapping(value = "order",produces = "application/json;charset=UTF-8")
public class OrderWeb {


    @GetMapping("list")
    @Operation(summary = "获取订单列表接口")
    private ApiResult getOrderList(){
        return ApiResult.success();
    }
}
