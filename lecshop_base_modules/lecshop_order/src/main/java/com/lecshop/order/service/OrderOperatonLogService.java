package com.lecshop.order.service;

import com.lecshop.order.bean.OrderOperatonLog;

import java.util.List;

/**
 * Created by dujinkai on 17/6/5.
 * 订单操作日志服务接口
 */
public interface OrderOperatonLogService {

    /**
     * 新增订单操作日志
     *
     * @param orderOperatonLog 订单操作日志
     * @return 成功返回1  失败返回0
     */
    int addOrderOperatonLog(OrderOperatonLog orderOperatonLog);

    /**
     * 根据订单id查询订单操作日志
     *
     * @param orderId 订单id
     * @return 返回订单的操作日志
     */
    List<OrderOperatonLog> queryOrderOperatonLogByOrderId(long orderId);
}
