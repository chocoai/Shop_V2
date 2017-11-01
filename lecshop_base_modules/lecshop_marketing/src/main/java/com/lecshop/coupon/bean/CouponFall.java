package com.lecshop.coupon.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 优惠卷直降实体类
 *
 * @author sunluyang on 2017/6/1.
 */
@Data
public class CouponFall {
    /**
     * 主键id
     */
    private long id;
    /**
     * 优惠卷id
     */
    private long couponId;
    /**
     * 直降金额
     */
    private BigDecimal price;
}
