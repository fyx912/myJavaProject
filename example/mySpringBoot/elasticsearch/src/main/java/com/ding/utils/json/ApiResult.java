package com.ding.utils.json;

import java.io.Serializable;

/**
 * @author ding
 * @create 22 21:44
 * @description
 */
public class ApiResult<T> implements Serializable {
    private Integer code;
    private String message;
    private String status;
    private T data;
    private String cause;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }


    public static <T> ApiResult<T> success(){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(StatusCodeEnum.SUCCESS.getCode());
        apiResult.setMessage(StatusCodeEnum.SUCCESS.getMessage());
        apiResult.setStatus("SUCCESS");
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
        apiResult.setStatus("FAILED");
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




}
