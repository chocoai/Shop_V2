package com.lecshop.order.service;

import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.order.bean.Order;
import com.lecshop.order.bean.QueryCriteria;
import com.lecshop.util.PageHelper;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/5.
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 分页查询订单
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回订单数据
     */
    PageHelper<Order> queryOrders(PageHelper<Order> pageHelper, QueryCriteria queryCriteria);

    /**
     * 查询所有店铺的订单
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回订单数据
     */
    PageHelper<Order> queryStoreOrders(PageHelper<Order> pageHelper, QueryCriteria queryCriteria);

    /**
     * 确认付款
     *
     * @param id            订单id
     * @param storeId       店铺id
     * @param operationName 操纵人
     * @return 成功返回1  失败返回0
     */
    int confirmOrder(long id, long storeId, String operationName);

    /**
     * 取消订单
     *
     * @param id            订单id
     * @param storeId       店铺id
     * @param operationName 操作人
     * @return 成功返回1 失败返回0
     */
    int cancelOrder(long id, long storeId, String operationName);

    /**
     * 修改价格
     *
     * @param id            订单id
     * @param price         修改的价格(不是最终价格)
     * @param reason        修改原因
     * @param storeId       店铺id
     * @param operationName 操作人
     * @return 成功返回 1 失败返回0
     */
    int modifyPrice(long id, BigDecimal price, String reason, long storeId, String operationName);

    /**
     * 查询订单的物流模版信息
     *
     * @param id      订单id
     * @param storeId 店铺id
     * @return 返回订单的物流模版
     */
    LogisticsTemplate queryLogisticsTemplateByOrderId(long id, long storeId);

    /**
     * 发货
     *
     * @param id            订单id
     * @param storeId       店铺id
     * @param waybillCode   运单号
     * @param operationName 操作人
     * @return 成功返回1 失败返回0
     */
    int deliverOrder(long id, long storeId, String waybillCode, String operationName);

    /**
     * 根据订单id查询订单信息 (订单的所有信息 此接口慎用)
     *
     * @param id      订单id
     * @param storeId 店铺id
     * @return 返回订单信息
     */
    Order queryOrderById(long id, long storeId);
}
