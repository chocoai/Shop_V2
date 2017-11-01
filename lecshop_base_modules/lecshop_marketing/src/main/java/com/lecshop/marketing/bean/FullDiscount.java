package com.lecshop.marketing.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/9.
 * 满折促销实体
 */
@Data
public class FullDiscount {

    /**
     * 促销id
     */
    private long id;

    /**
     * 促销id
     */
    private long marketingId;

    /**
     * 满多少钱
     */
    private BigDecimal fullPrice;

    /**
     * 打多少折
     */
    private BigDecimal discount;
}
