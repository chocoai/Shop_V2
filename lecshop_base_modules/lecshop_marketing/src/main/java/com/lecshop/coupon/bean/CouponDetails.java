package com.lecshop.coupon.bean;

import lombok.Data;

import java.util.List;

/**
 * 优惠券详情实体类
 *
 * @author sunluyang on 2017/6/6.
 */
@Data
public class CouponDetails {
    /**
     * 优惠券信息实体类
     */
    private Coupon coupon;
    /**
     * 优惠券券码实体类
     */
    private List<CouponCode> couponCode;

    public CouponDetails(Coupon coupon, List<CouponCode> couponCode) {
        super();
        this.coupon = coupon;
        this.couponCode = couponCode;
    }
}
