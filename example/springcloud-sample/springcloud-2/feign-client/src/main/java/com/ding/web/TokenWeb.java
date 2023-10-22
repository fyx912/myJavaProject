package com.ding.web;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName TokenWeb
 * @date 2022-01-13 11:48
 */
@RestController
@RequestMapping(value = "token")
public class TokenWeb {
    private Logger log = LoggerFactory.getLogger(TokenWeb.class);

    @GetMapping
    public String token(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        log.info("token=={}",token);
        if (StringUtils.isEmpty(token))
            return  resultJson(1,"failed");
        return resultJson();
    }
    @PostMapping
    public String tokenPost(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        log.info("token=={}",token);
        if (StringUtils.isEmpty(token))
            return  resultJson(1,"failed");
        return resultJson();
    }

    public String resultJson(){
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","success");
        return json.toString();
    }
    public String resultJson(Integer code,String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toString();
    }
}
