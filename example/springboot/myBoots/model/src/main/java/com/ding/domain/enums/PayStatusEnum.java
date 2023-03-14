package com.ding.domain.enums;

/**
 * @author ding
 * @create 27 17:37
 * @description
 */
public enum PayStatusEnum {
    UNPAID(1,"待支付"),
    HAVE_PAID(2,"已支付"),
    FAILED_PAID(3,"支付失败"),
    ;

    PayStatusEnum(Integer status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    private Integer status;
    private String statusName;
}
