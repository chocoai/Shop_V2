package com.lecshop.order.bean;

import lombok.Data;

/**
 * 订单设置实体类
 *
 * Created by LecShop on 2017/6/5.
 */
@Data
public class OrderSetting {

    /**
     * 主键id
     */
    private long id;

    /**
     * 是否可以退款和退货  0 可以 1 不可以 默认0
     */
    private String allowBack;

    /**
     * 订单自定收货几天前的订单 默认1
     */
    private int aotuConfirm;

    /**
     * 退款说明
     */
    private String refundsDesc;

    /**
     * 退货说明
     */
    private String returnDesc;

    /**
     * 是否支持货到付款  0 支付 1 不支持 默认0
     */
    private String cashonDelivery;

}
