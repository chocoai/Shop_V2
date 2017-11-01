package com.lecshop.coupon.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 优惠卷卷码实体类
 *
 * @author sunluyang on 2017/6/1.
 */
@Data
public class CouponCode {
    /**
     * 主键id
     */
    private long id;
    /**
     * 优惠卷id
     */
    private long couponId;
    /**
     * 优惠卷的卷码
     */
    private String code;
    /**
     * 领取优惠卷的会员id
     */
    private long customerId;
    /**
     * 优惠卷状态  0 未领取 1已领取未使用 2 已使用 3 已失效
     */
    private String status;
    /**
     * 领取时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime receiveTime;
    /**
     * 会员名称（用于连会员表查询）
     */
    private String username;

    public CouponCode(long id, long couponId, String code, long customerId, String status, LocalDateTime receiveTime, String username) {
        super();
        this.id = id;
        this.couponId = couponId;
        this.code = code;
        this.customerId = customerId;
        this.status = status;
        this.receiveTime = receiveTime;
        this.username = username;
    }

    public CouponCode() {
    }
}
