package com.lecshop.order.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/5.
 * 订单搜索条件
 */
@Data
public class QueryCriteria {

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 用户名
     */
    private String customerName;

    /**
     * 支付类型  1在线支付  2货到付款
     */
    private String payType;

    /**
     * 订单状态
     * 空:全部
     * 1:待付款  （用户刚下单）
     * 2:代发货  （用户付完款 等待商城发货）
     * 3:代收货  （商城已经发货 等待用户确认收货）
     * 4:已完成  （用户已经确认收货 订单结束）
     * 5:已关闭 （用户未付款前取消订单）
     */
    private String status;


    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 店铺id
     */
    private long storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 获得查询的参数
     *
     * @return 返回查询的参数
     */
    public Map<String, Object> getQueryMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("orderCode", this.orderCode);
        params.put("customerName", this.customerName);
        params.put("payType", this.payType);
        params.put("status", this.status);
        params.put("startTime", this.startTime);
        params.put("endTime", this.endTime);
        params.put("storeId", this.storeId);
        params.put("storeName", this.storeName);
        return params;
    }

}
