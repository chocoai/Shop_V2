package com.lecshop.order.mapper;

import com.lecshop.order.bean.OrderSetting;
import org.springframework.stereotype.Repository;

/**
 * 订单设置数据库接口
 *
 * Created by LecShop on 2017/6/5.
 */
@Repository
public interface OrderSettingMapper {

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
