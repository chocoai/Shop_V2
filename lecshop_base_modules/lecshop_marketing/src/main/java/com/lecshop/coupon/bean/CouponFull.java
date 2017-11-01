package com.lecshop.coupon.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 优惠券满减实体类
 *
 * @author sunluyang on 2017/6/1.
 */
@Data
public class CouponFull {
    /**
     * 主键id
     */
    private long id;
    /**
     * 优惠卷id
     */
    private long couponId;
    /**
     * 满多少钱
     */
    private BigDecimal fullPrice;
    /**
     * 减多少钱
     */
    private BigDecimal price;
}
