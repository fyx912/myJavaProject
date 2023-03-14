package com.ding.domain.sys;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ding
 * @create 27 17:21
 * @description 商品
 */
@Schema(name = "商品实体类",description = "商品")
public class Commodity {
    @Schema(title = "商品id", required = false, example = "123")
    private String cid;
    /**
     * {@link com.ding.domain.enums.CommodityTypeEnum}
     */
    @Schema(title = "商品类型", required = true, example = "1")
    private Integer type;
    //商品名称
    @Schema(title = "商品名称", required = true, example = "手机")
    private String commodityName;
    //商品价格
    @Schema(title = "商品价格", required = true, example = "100.00")
    private BigDecimal retailPrice;
    //储备量
    @Schema(title = "库存量", required = true, example = "100")
    private Integer stock;
    @Schema(title = "创建时间", required = false)
    private Date  createTime;
    @Schema(title = "更新时间", required = false)
    private Date  updateTime;
    @Schema(title = "描述", required = false)
    private String description; //描述

    public Commodity() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
