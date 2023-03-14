package com.ding.domain.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName Order
 * @date 2020-04-18 19:38
 */
@Data
@Schema(name = "订单实体类",description = "订单")
public class Order {
    @Schema(title = "订单id", required = true)
    private  String oid;
    @Schema(title = "用户id", required = true)
    private String uid;
    @Schema(title = "任务id", required = true)
    private String tid;
    @Schema(title = "订单编号", required = true)
    private  String orderNo; //订单编号
    /**
     * {@link com.ding.domain.enums.OrderStatusEnum}
     */
    @Schema(title = "订单状态", required = true)
    private  String  orderStatus;//订单状态
    @Schema(title = "订单金额", required = true)
    private BigDecimal orderMoney;
    @Schema(title= "支付流水号", required = true)
    private String payNo; //支付流水号
}
