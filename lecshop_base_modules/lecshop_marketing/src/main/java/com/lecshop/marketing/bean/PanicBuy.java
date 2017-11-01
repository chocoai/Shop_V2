package com.lecshop.marketing.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/8.
 * 抢购实体
 */
@Data
public class PanicBuy {

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

    /**
     * 限购个数
     */
    private int limitNum;

    /**
     * PC端抢购图片
     */
    private String pcUrl;

    /**
     * 手机端抢购图片
     */
    private String mobileUrl;
}
