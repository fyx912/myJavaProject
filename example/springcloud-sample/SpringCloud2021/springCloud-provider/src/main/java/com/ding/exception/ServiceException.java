package com.ding.exception;

import org.springframework.util.StringUtils;

public class ServiceException extends RuntimeException{

    private String msg;
    private Integer code;


    public ServiceException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String json) {
        this.msg = json;
    }

    public ServiceException(String msg,Throwable cause){
        super(msg,cause);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
