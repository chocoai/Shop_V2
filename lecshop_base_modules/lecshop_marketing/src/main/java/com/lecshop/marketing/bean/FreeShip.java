package com.lecshop.marketing.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/9.
 * 包邮促销实体
 */
@Data
public class FreeShip {

    /**
     * 主键id
     */
    private long id;

    /**
     * 促销id
     */
    private long marketingId;

    /**
     * 包邮满的价格
     */
    private BigDecimal fullPrice;
}
