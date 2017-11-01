package com.lecshop.marketingmanager.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.coupon.bean.Coupon;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 注册营销表实体类
 *
 * Created by LecShop on 2017/6/6.
 */
@Data
public class RegisterMarketing {

    /**
     * 主键id
     */
    private long id;

    /**
     * 是否开启  0未开启 1开启 默认0
     */
    private String isUse;

    /**
     * 送优惠卷的id
     */
    private long couponId;

    /**
     * 送积分的数量
     */
    private int pointNum;

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
     * 优惠券集合
     */
    private List<Coupon> couponList;

}
