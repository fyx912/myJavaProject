package com.ding.domain.enums;

/**
 * @author ding
 * @create 27 17:37
 * @description
 */
public enum PaymentEnum {
    OFFLINE_PAYMENT(1,"线下支付","offline payment"),
    WeChat_PAY(2,"微信支付","WeChat Pay"),
    ALI_PAY(3,"支付宝","AliPay"),
    ALLIN_PAY(4,"通联支付","Allinpay"),
    ;

    PaymentEnum(Integer type, String typeName,String englishName) {
        this.type = type;
        this.typeName = typeName;
        this.englishName = englishName;
    }

    private Integer type;
    private String typeName;
    private String englishName;
}
