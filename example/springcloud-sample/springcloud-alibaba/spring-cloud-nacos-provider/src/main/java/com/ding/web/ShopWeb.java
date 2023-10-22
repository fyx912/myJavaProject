package com.ding.web;

import com.alibaba.fastjson.JSONObject;
import com.ding.model.Shop;
import com.ding.api.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName ShopWeb
 * @date 2022-01-18 16:38
 */
@RestController
@RequestMapping("shop")
public class ShopWeb {
    @Resource
    private ShopService shopService;

    @GetMapping("list")
    public String  shopList(){
        List<Shop> list= shopService.shopList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("msg","success");
        jsonObject.put("data",list);
        return jsonObject.toJSONString();
    }
}
