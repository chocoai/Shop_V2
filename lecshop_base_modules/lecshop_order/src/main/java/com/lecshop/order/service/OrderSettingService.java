package com.lecshop.order.service;

import com.lecshop.order.bean.OrderSetting;

/**
 * 订单设置service
 *
 * Created by LecShop on 2017/6/5.
 */
public interface OrderSettingService {

    /**
     * 查找订单设置
     *
     * @return 返回订单设置
     */
    OrderSetting queryOrderSetting();

    /**
     * 修改订单设置
     *
     * @param orderSetting 订单设置
     * @return             成功返回1  失败返回0
     */
    int updateOrderSetting(OrderSetting orderSetting);
}
