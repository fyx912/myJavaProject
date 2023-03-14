package com.ding.domain.pay;

import com.ding.domain.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ding
 * @create 27 17:19
 * @description
 */
@Data
public class Pay {
    private  String payId;
    private  String payNo; //支付流水号
    /**
     * 支付方式
     *
     * {@link  com.ding.domain.enums.PaymentEnum}
     */
    private Integer payment;
    /**
     * {@link  PayStatusEnum}
     */
    private  Integer  payStatus;//支付状态
    private BigDecimal money;//支付金额
    private String payDesc; //描述
}
