package com.lecshop.marketing.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/8.
 * 满减促销实体
 */
@Data
public class FullDown {

    /**
     * 主键id
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
     * 减多少钱
     */
    private BigDecimal price;
}
