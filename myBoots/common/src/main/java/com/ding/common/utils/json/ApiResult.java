package com.ding.common.utils.json;

import java.io.Serializable;

/**
 * @author ding
 * @create 27 2:43
 * @description
 */
public class ApiResult<T> implements Serializable {
    //状态码
    private Integer code;
    //业务提示信息
    private String  message;
    //错误信息
    private String cause;
    //对象数据
    private T  data;

    public static <T> ApiResult<T> success(){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(StatusCodeEnum.SUCCESS.getCode());
        apiResult.setMessage(StatusCodeEnum.SUCCESS.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data){
        ApiResult<T> apiResult= ApiResult.success();
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> failed(){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(StatusCodeEnum.FAILED.getCode());
        apiResult.setMessage(StatusCodeEnum.FAILED.getMessage());
        return apiResult;
    }
    public static <T> ApiResult<T> failed(String cause){
        ApiResult<T> apiResult = failed();
        apiResult.setCause(cause);
        return apiResult;
    }

    public static <T> ApiResult<T> failed(Integer code,String message){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return apiResult;
    }

    public static <T> ApiResult<T> failed(Integer code,String message,String cause){
        ApiResult<T> apiResult = failed(code,message);
        apiResult.setCause(cause);
        return apiResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
