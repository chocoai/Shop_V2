package com.lecshop.marketing.bean;

import com.lecshop.sku.bean.Sku;
import lombok.Data;

/**
 * Created by dujinkai on 17/6/8.
 * 营销关联的单品信息
 */
@Data
public class MarketingSku {

    /**
     * 主键id
     */
    private long id;

    /**
     * 单品id
     */
    private String skuId;

    /**
     * 促销id
     */
    private long marketingId;

    /**
     * 单品信息
     */
    private Sku sku;
}
