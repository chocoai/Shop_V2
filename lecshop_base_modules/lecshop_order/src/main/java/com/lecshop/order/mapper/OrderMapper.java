package com.lecshop.order.mapper;

import com.lecshop.order.bean.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/5.
 * 订单数据库接口
 */
public interface OrderMapper {

    /**
     * 查询订单总数
     *
     * @param params 查询参数
     * @return 返回订单总数
     */
    int queryOrderCount(Map<String, Object> params);

    /**
     * 分页查询订单
     *
     * @param params 查询参数
     * @return 返回订单
     */
    List<Order> queryOrders(Map<String, Object> params);

    /**
     * 确认付款
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int confirmOrder(Map<String, Object> params);

    /**
     * 取消订单
     *
     * @param params 参数
     * @return 成功返回 1 失败返回0
     */
    int cancelOrder(Map<String, Object> params);

    /**
     * 修改价格
     *
     * @param params 参数
     * @return 成功返回1  失败返回0
     */
    int modifyPrice(Map<String, Object> params);

    /**
     * 查询订单的运费模版id
     *
     * @param params 查询参数
     * @return 返回订单的运费模版id
     */
    long queryTemplateId(Map<String, Object> params);

    /**
     * 发货
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int deliverOrder(Map<String, Object> params);

    /**
     * 根据订单id查询订单信息
     *
     * @param params 查询参数
     * @return 返回订单信息
     */
    Order queryOrderById(Map<String, Object> params);

    /**
     * 查询所有店铺订单总数
     *
     * @param params 查询参数
     * @return 返回所有店铺订单总数
     */
    int queryStoreOrdersCount(Map<String, Object> params);

    /**
     * 查询所有店铺订单
     *
     * @param params 查询参数
     * @return 返回所有店铺订单
     */
    List<Order> queryStoreOrders(Map<String, Object> params);
}
