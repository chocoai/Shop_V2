package com.lecshop.marketing.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/8.
 * 直降促销实体
 */
@Data
public class Fall {

    /**
     * 主键id
     */
    private long id;

    /**
     * 促销id
     */
    private long marketingId;

    /**
     * 直降金额
     */
    private BigDecimal price;
}
