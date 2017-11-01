package com.lecshop.order.service.impl;

import com.lecshop.order.bean.OrderAttr;
import com.lecshop.order.mapper.OrderAttrMapper;
import com.lecshop.order.service.OrderAttrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dujinkai on 17/6/6.
 * 订单附属信息服务接口实现
 */
@Service
public class OrderAttrServiceImpl implements OrderAttrService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(OrderAttrServiceImpl.class);

    /**
     * 注入订单附属信息数据库口
     */
    @Autowired
    private OrderAttrMapper orderAttrMapper;

    @Override
    public OrderAttr queryByOrderId(long orderId) {
        logger.debug("queryByOrderId and orderId:{}", orderId);
        return orderAttrMapper.queryByOrderId(orderId);
    }
}
