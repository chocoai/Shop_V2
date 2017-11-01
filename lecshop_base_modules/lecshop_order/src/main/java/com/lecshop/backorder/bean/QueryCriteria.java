package com.lecshop.backorder.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/6.
 * 退单搜索条件实体
 */
@Data
public class QueryCriteria {
    /**
     * 退单号
     */
    private String backCode;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 退款／退货状态
     * 1:退款申请 （用户发送退款请求）
     * 2:退款成功（商家同意退款）
     * 3:退款拒绝 （商家拒绝退款）
     * 4:退货申请 （用户发起退货请求）
     * 5:退货拒绝   （商家拒绝退货）
     * 6:退货审核通过等待用户填写物流（商家审核通过，等待用户寄回商品）
     * 7: 待商家收货  （用户已经寄回商品，等待商家收货确认）
     * 8：退货完成（商家收货并且同意退款给用户）
     * 9:退货失败（商家不同意退款）
     * 空 查询全部
     */
    private String status;

    /**
     * 店铺id  平台的为0
     */
    private long storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 获得查询的参数
     *
     * @return 返回查询参数
     */
    public Map<String, Object> getQueryMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("backCode", this.backCode);
        params.put("orderCode", this.orderCode);
        params.put("status", this.status);
        params.put("storeId", this.storeId);
        params.put("storeName", this.storeName);
        return params;
    }
}
