package com.ding.model;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName Shop
 * @date 2022-01-18 16:42
 */
public class Shop {
    private Integer id;
    private String productName;
    private Integer stock;
    private Double productPrice;

    public Shop() {
    }

    public Shop(Integer id, String productName, Integer stock, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.stock = stock;
        this.productPrice = productPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
