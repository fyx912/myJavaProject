package com.ding.web;

import com.ding.entity.User;
import com.ding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ding
 * @create 20230313 23:29
 * @description
 */
@RestController
public class UserWeb {
    @Autowired
    private UserService userService;
    private static Map map = new HashMap();

    static void init(){
        map.put("code",0);
        map.put("msg","success");
    }

    @GetMapping
    public String index(){
        return "hello";
    }

    @GetMapping("list")
    public Object findAl(){
        List<User> list = userService.findAll();
        map.put("data",list);
        return map;
    }
    @GetMapping("user/{uid}")
    public Object findUserByUid(@PathVariable String uid){
        User user = userService.findUserByUid(uid);
        map.put("data",user);
        return map;
    }
}
