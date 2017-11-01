package com.lecshop.order.service;

import com.lecshop.order.bean.OrderAttr;

/**
 * Created by dujinkai on 17/6/6.
 * 订单附属信息服务接口
 */
public interface OrderAttrService {

    /**
     * 根据订单id查询订单的附属信息
     *
     * @param orderId 订单id
     * @return 返回订单附属信息
     */
    OrderAttr queryByOrderId(long orderId);
}
