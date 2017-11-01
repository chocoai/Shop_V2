package com.lecshop.order.service.impl;

import com.lecshop.order.bean.OrderOperatonLog;
import com.lecshop.order.mapper.OrderOperatonLogMapper;
import com.lecshop.order.service.OrderOperatonLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 17/6/5.
 * 订单操作日志服务接口实现
 */
@Service
public class OrderOperatonLogServiceImpl implements OrderOperatonLogService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(OrderOperatonLogServiceImpl.class);

    /**
     * 订单操作日志数据库接口
     */
    @Autowired
    private OrderOperatonLogMapper orderOperatonLogMapper;

    @Override
    public int addOrderOperatonLog(OrderOperatonLog orderOperatonLog) {
        if (Objects.isNull(orderOperatonLog)) {
            logger.error("addOrderOperatonLog fail due to orderOperatonLog is null....");
            return 0;
        }
        return orderOperatonLogMapper.addOrderOperatonLog(orderOperatonLog);
    }

    @Override
    public List<OrderOperatonLog> queryOrderOperatonLogByOrderId(long orderId) {
        logger.debug("queryOrderOperatonLogByOrderId and orderId:{}", orderId);
        return orderOperatonLogMapper.queryOrderOperatonLogByOrderId(orderId);
    }
}
