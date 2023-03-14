package com.ding.domain.enums;

/**
 * @author ding
 * @create 27 17:37
 * @description
 */
public enum OrderStatusEnum {
    PENDING(1,"待处理"),
    UNPAID(2,"待支付"),
    HAVE_PAID(3,"已支付"),
    DONE(4,"已完成")
    ;

    OrderStatusEnum(Integer status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    private Integer status;
    private String statusName;
}
