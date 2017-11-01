package com.lecshop.order.mapper;

import com.lecshop.order.bean.OrderSku;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/6.
 * 订单单品数据库接口
 */
public interface OrderSkuMapper {

    /**
     * 根据订单id查询订单单品
     *
     * @param orderId 订单id
     * @return 返回订单单品信息
     */
    List<OrderSku> queryByOrderId(long orderId);

    /**
     * 根据订单id和单品id查询订单商品
     *
     * @param params 查询参数
     * @return 返回订单商品
     */
    OrderSku queryByOrderIdAndSkuId(Map<String, Object> params);
}
