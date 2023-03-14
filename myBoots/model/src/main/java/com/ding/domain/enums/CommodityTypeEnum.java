package com.ding.domain.enums;

/**
 * @author ding
 * @create 27 17:24
 * @description  商品类型
 */
public enum CommodityTypeEnum {
    COMMON_PRODUCTS(1,"日化用品"),
    ELECTRONIC_PRODUCTS(2,"电子产品"),
    HOME_APPLIANCES(4,"家用器具"),
    TOURISM_PRODUCTS(3,"旅游产品")
            ;

    private Integer type;
    private String name;

    CommodityTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
