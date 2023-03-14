package com.ding.boots.web.sys;

import com.ding.boots.service.sys.CommodityService;
import com.ding.common.utils.json.ApiResult;
import com.ding.domain.sys.Commodity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author ding
 * @create 28 18:21
 * @description
 */
@Tag(name = "CommodityWeb",description = "商品")
@RestController
@RequestMapping(value = "commodity",produces = "application/json;charset=UTF-8")
public class CommodityWeb {
    @Resource
    private CommodityService commodityService;

    @GetMapping("list")
    @Operation(summary = "获取商品列表接口")
    public ApiResult commodityList(){
        return commodityService.commodityList();
    }

    @PostMapping("add")
    @Operation(summary = "新增商品接口")
    private ApiResult add(@RequestBody Commodity commodity){
        return commodityService.addCommodity(commodity) ;
    }

    @PostMapping("delete/{cid}")
    @Operation(summary = "删除商品接口")
    private ApiResult delete(@PathVariable("cid")  String cid){
        return commodityService.deleteCommodity(cid) ;
    }

    @PutMapping("update")
    @Operation(summary = "修改商品接口")
    private ApiResult update(@RequestBody Commodity commodity){
        return commodityService.update(commodity) ;
    }
}
