package com.lecshop.coupon.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * 优惠券实体类
 *
 * @author sunluyang on 2017/6/1.
 */
@Data
public class Coupon {
    /**
     * 主键id
     */
    private long id;
    /**
     * 优惠卷名称
     */
    private String name;
    /**
     * 生成张数
     */
    private int num;
    /**
     * 每人可以领取的张数
     */
    private int limitNum;
    /**
     * 类型 1满减 2直降
     */
    private int type;
    /**
     * 描述
     */
    private String desc;
    /**
     * 店铺id   平台的优惠卷为0
     */
    private long storeId;
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
     * 删除标记  0未删除 1删除 默认0
     */
    private int delFlag;
    /**
     * 满多少钱(满减中使用,直降中不用该属性)
     */
    private BigDecimal fullPrice;
    /**
     * 减多少钱
     */
    private BigDecimal price;

    /**
     * 判断开始时间是否大于结束时间
     *
     * @return 开始时间大于结束时间返回true,小于false
     */
    public boolean toCompareTime() {
        return this.getStartTime().isAfter(this.getEndTime());
    }
}
