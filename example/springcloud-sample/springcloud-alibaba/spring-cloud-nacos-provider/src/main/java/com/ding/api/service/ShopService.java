package com.ding.api.service;

import com.ding.model.Shop;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName ShopService
 * @date 2022-01-18 16:40
 */
@Service
public class ShopService {
    public List<Shop> list = new LinkedList<>();

    public List<Shop> shopList(){
        list.add(new Shop(1,"XiaoMi phone",5000,2000.00));
        list.add(new Shop(2," iphone 13",100,5999.00));
        list.add(new Shop(3,"apple",10000,5.00));
        return list;
    }
}
