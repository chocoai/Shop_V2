package com.lecshop.order.service;

import com.lecshop.order.bean.OrderSku;

import java.util.List;

/**
 * Created by dujinkai on 17/6/6.
 * 订单单品服务接口
 */
public interface OrderSkuService {

    /**
     * 根据订单id查询订单商品信息
     *
     * @param orderId 订单id
     * @return 返回订单商品信息
     */
    List<OrderSku> queryByOrderId(long orderId);

    /**
     * 根据订单id 和单品id 查询订单商品
     *
     * @param orderId 订单id
     * @param skuIds  单品id  多个单品用,隔开
     * @return 返回订单商品信息
     */
    List<OrderSku> queryByOrderIdAndSkuIds(long orderId, String skuIds);
}
