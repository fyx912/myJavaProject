package com.ding.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-10-22 02:17:43
 **/
@Getter
public class ApiResponse {
    private Integer code;
    private String message;
    private Object data;

    public static <T> ApiResponse success(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(0);
        apiResponse.setMessage("success");
        return apiResponse;
    }

    public static ApiResponse success(Object data){
        ApiResponse apiResponse = success();
        apiResponse.setData(data);
        return apiResponse;
    }

    public static Map<String,Object> authorization(String authorization){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("message","success");
        map.put("authorization",authorization);
        return map;
    }

    public static  ApiResponse failed(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1);
        apiResponse.setMessage("failed");
        return apiResponse;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
