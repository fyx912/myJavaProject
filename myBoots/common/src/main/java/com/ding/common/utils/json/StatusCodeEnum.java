package com.ding.common.utils.json;

/**
 * @author ding
 * @create 27 3:38
 * @description
 */
public enum StatusCodeEnum {
    SUCCESS(0,"success"),
    FAILED(1,"failed"),
    PARAM(2,"请求参数不合法!"),
    SIGN_IN(100,"账号或密码有误!")
    ;

    private Integer code;
    private String message;

    StatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
