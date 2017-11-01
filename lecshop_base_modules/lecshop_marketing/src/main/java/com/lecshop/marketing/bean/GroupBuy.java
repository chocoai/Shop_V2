package com.lecshop.marketing.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/8.
 * 团购实体
 */
@Data
public class GroupBuy {

    /**
     * 主键id
     */
    private long id;

    /**
     * 促销id
     */
    private long marketingId;

    /**
     * 折扣
     */
    private BigDecimal discount;
}
