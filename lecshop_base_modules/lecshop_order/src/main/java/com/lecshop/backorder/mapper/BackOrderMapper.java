package com.lecshop.backorder.mapper;

import com.lecshop.backorder.bean.BackOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/6.
 * 退单数据库接口
 */
public interface BackOrderMapper {

    /**
     * 查询退单列表总数
     *
     * @param params 查询参数
     * @return 返回退单列表总数
     */
    int queryBackOrderCount(Map<String, Object> params);

    /**
     * 查询退单列表
     *
     * @param params 查询参数
     * @return 返回退单列表
     */
    List<BackOrder> queryBackOrders(Map<String, Object> params);

    /**
     * 根据退单id查询退单信息
     *
     * @param params 查询参数
     * @return 返回退单信息
     */
    BackOrder queryBackOrderById(Map<String, Object> params);

    /**
     * 同意退款
     *
     * @param params 参数
     * @return 成功返回1  失败返回0
     */
    int agreeToRefund(Map<String, Object> params);

    /**
     * 拒绝退款
     *
     * @param params 参数
     * @return 成功返回 1 失败返回0
     */
    int refuseRefund(Map<String, Object> params);

    /**
     * 同意退货
     *
     * @param params 参数
     * @return 成功返回1  失败返回0
     */
    int agreeToReturn(Map<String, Object> params);

    /**
     * 具拒绝退货
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int refuseRrturn(Map<String, Object> params);

    /**
     * 同意退货确认收货
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int confirmReturn(Map<String, Object> params);

    /**
     * 拒绝收货
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int refuseToReceive(Map<String, Object> params);

    /**
     * 查询店铺退单总数
     *
     * @param params 查询参数
     * @return 返回店铺退单总数
     */
    int queryStoreBackOrdersCount(Map<String, Object> params);

    /**
     * 查询店铺退单
     *
     * @param params 查询参数
     * @return 返回店铺退单列表
     */
    List<BackOrder> queryStoreBackOrders(Map<String, Object> params);
}
