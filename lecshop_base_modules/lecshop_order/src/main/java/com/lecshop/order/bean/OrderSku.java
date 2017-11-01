package com.lecshop.order.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/6.
 * 订单单品信息实体
 */
@Data
public class OrderSku {

    /**
     * 主建id
     */
    private long id;

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 单品id
     */
    private long skuId;

    /**
     * 单品数量
     */
    private int num;

    /**
     * 单品的最终价格（原价－优惠价）
     */
    private BigDecimal price;

    /**
     * 退货时候应该退的价格
     */
    private BigDecimal backPrice;

    /**
     * 满减／满折 营销的id
     */
    private long fullpriceMarketingId;

    /**
     * 使用营销的id(直降，团购，抢购的id)
     */
    private long marketingId;

    /**
     * 单品的价格
     */
    private BigDecimal skuPrice;

    /**
     * 单品的名称
     */
    private String skuName;

    /**
     * 单品的编号
     */
    private String skuNo;

    /**
     * 单品的图片
     */
    private String skuImage;

    /**
     * 单品的规格
     */
    private String skuSpecs;
}
