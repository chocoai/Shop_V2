package com.lecshop.backorder.service;

import com.lecshop.backorder.bean.BackOrder;
import com.lecshop.backorder.bean.QueryCriteria;
import com.lecshop.util.PageHelper;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/6.
 * 退单服务接口
 */
public interface BackOrderService {

    /**
     * 分页查询退单列表
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回退单列表
     */
    PageHelper<BackOrder> queryBackOrders(PageHelper<BackOrder> pageHelper, QueryCriteria queryCriteria);

    /**
     * 查询店铺退单列表
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回店铺退单列表
     */
    PageHelper<BackOrder> queryStoreBackOrders(PageHelper<BackOrder> pageHelper, QueryCriteria queryCriteria);

    /**
     * 根据退单id查询退单信息  (此接口会返回退单的单品信息 ,操作日志   请慎重调用)
     *
     * @param backOrderId 退单id
     * @param storeId     店铺id
     * @return 返回退单信息
     */
    BackOrder queryBackOrderById(long backOrderId, long storeId);

    /**
     * 同意退款
     *
     * @param backOrderId 退单id
     * @param storeId     店铺id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    int agreeToRefund(long backOrderId, long storeId, String message);

    /**
     * 拒绝退款
     *
     * @param backOrderId 退单id
     * @param storeId     店铺id
     * @param message     留言
     * @return 成功返回1 失败返回0 当前退单状态不对 返回-1
     */
    int refuseRefund(long backOrderId, long storeId, String message);

    /**
     * 同意退货
     *
     * @param backOrderId 退单id
     * @param storeId     店铺id
     * @param message     留言
     * @return 成功返回1 失败返回0 当前退单状态不对 返回-1
     */
    int agreeToReturn(long backOrderId, long storeId, String message);

    /**
     * 拒绝退货
     *
     * @param backOrderId 退单id
     * @param storeId     店铺id
     * @param message     留言
     * @return 成功返回1 失败返回0 当前退单状态不对 返回-1
     */
    int refuseReturn(long backOrderId, long storeId, String message);

    /**
     * 同意确认退货
     *
     * @param backOrderId 退单id
     * @param storeId     店铺id
     * @param message     留言
     * @param money       实退金额
     * @return 成功返回1 失败返回0 当前退单状态不对 返回-1
     */
    int confirmReturn(long backOrderId, long storeId, String message, BigDecimal money);

    /**
     * 拒绝收货
     *
     * @param backOrderId 退单id
     * @param storeId     店铺id
     * @param message     留言
     * @return 成功返回1 失败返回0 当前退单状态不对 返回-1
     */
    int refuseToReceive(long backOrderId, long storeId, String message);
}
