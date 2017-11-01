package com.lecshop.order.service.impl;

import com.lecshop.order.bean.OrderSetting;
import com.lecshop.order.mapper.OrderSettingMapper;
import com.lecshop.order.service.OrderSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单设置service实现类
 *
 * Created by LecShop on 2017/6/5.
 */
@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    /**
     * 自动注入订单设置数据库接口
     */
    @Autowired
    private OrderSettingMapper orderSettingMapper;

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(OrderSettingServiceImpl.class);

    /**
     * 查找订单设置
     *
     * @return 返回订单设置
     */
    @Override
    public OrderSetting queryOrderSetting() {
        logger.debug("queryOrderSetting...");
        return orderSettingMapper.queryOrderSetting();
    }

    /**
     * 修改订单设置
     *
     * @param orderSetting 订单设置
     * @return 成功返回1  失败返回0
     */
    @Override
    public int updateOrderSetting(OrderSetting orderSetting) {
        logger.debug("updateOrderSetting and orderSetting :{}", orderSetting);
        return orderSettingMapper.updateOrderSetting(orderSetting);
    }
}
