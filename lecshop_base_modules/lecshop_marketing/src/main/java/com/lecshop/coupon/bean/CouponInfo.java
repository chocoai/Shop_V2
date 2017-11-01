package com.lecshop.coupon.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户领取优惠券信息实体类
 *
 * @author sunluyang on 2017/7/5.
 */
@Data
public class CouponInfo {
    /**
     * 优惠券券码表中自增长id
     */
    private long id;
    /**
     * 优惠券id
     */
    private long couponId;
    /**
     * 优惠券券码
     */
    private String code;
    /**
     * 会员id
     */
    private long customerId;
    /**
     * 优惠券状态
     */
    private String status;
    /**
     * 领取时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime receiveTime;
    /**
     * 类型 1满减 2直降
     */
    private int type;
    /**
     * 店铺id
     */
    private long storeId;
    /**
     * 优惠券名称
     */
    private String name;
    /**
     * 优惠券描述
     */
    private String desc;
    /**
     * 开始时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime endTime;
    /**
     * 优惠券是否删除  0 未删除 1 删除
     */
    private String delFlag;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺状态  0 填写资料中  1 店铺审核中 2 审核通过 3 审核不通过 4 店铺关闭
     */
    private String storeStatus;
    /**
     * 店铺是否删除 0 未删除 1 删除
     */
    private String storeDelFlag;
    /**
     * 直降金额
     */
    private BigDecimal fallPrice;
    /**
     * 满多少钱(满减中使用,直降中不用该属性)
     */
    private BigDecimal fullPrice;
    /**
     * 减多少钱
     */
    private BigDecimal price;

    /**
     * 获取优惠券领取信息
     *
     * @return 优惠券领取信息实体类
     */
    @JsonIgnore
    public CouponInfo getCouponInfoToChangeStoreName() {
        if (this.storeId == 0) {
            this.storeName = "乐商商城自营";
        }
        return this;
    }
}
