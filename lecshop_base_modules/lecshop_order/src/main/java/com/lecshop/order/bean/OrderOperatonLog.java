package com.lecshop.order.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/6/5.
 * 订单操作日志
 */
@Data
public class OrderOperatonLog {

    /**
     * 主键id
     */
    private long id;

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 操作类型 1 修改状态 2 修改金额 3 发货  4取消订单
     */
    private String type;

    /**
     * 操作说明
     */
    private String remark;

    /**
     * 操作人
     */
    private String operationName;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 构造取消订单的操作日志
     *
     * @param orderId       订单id
     * @param operationName 操作人
     * @return 返回订单操作日志
     */
    public static OrderOperatonLog buildForCancelOrdere(long orderId, String operationName) {
        OrderOperatonLog orderOperatonLog = new OrderOperatonLog();
        orderOperatonLog.orderId = orderId;
        orderOperatonLog.type = "4";
        orderOperatonLog.operationName = operationName;
        orderOperatonLog.createTime = LocalDateTime.now();
        return orderOperatonLog;
    }

    /**
     * 构造发货的订单操作日志
     *
     * @param orderId       订单id
     * @param operationName 操作人
     * @return 返回订单操作日志
     */
    public static OrderOperatonLog buildForDeliverOrder(long orderId, String operationName) {
        OrderOperatonLog orderOperatonLog = new OrderOperatonLog();
        orderOperatonLog.orderId = orderId;
        orderOperatonLog.type = "3";
        orderOperatonLog.operationName = operationName;
        orderOperatonLog.createTime = LocalDateTime.now();
        return orderOperatonLog;
    }

    /**
     * 构造修改价格的订单操作日志
     *
     * @param orderId       订单id
     * @param remark        备注
     * @param operationName 操作人
     * @return 返回订单操作日志
     */
    public static OrderOperatonLog buildForModifyPrice(long orderId, String remark, String operationName) {
        OrderOperatonLog orderOperatonLog = new OrderOperatonLog();
        orderOperatonLog.orderId = orderId;
        orderOperatonLog.type = "2";
        orderOperatonLog.remark = remark;
        orderOperatonLog.operationName = operationName;
        orderOperatonLog.createTime = LocalDateTime.now();
        return orderOperatonLog;
    }

    /**
     * 构造确认付款的订单操作日志
     *
     * @param orderId       订单id
     * @param operationName 操作人
     * @return 返回订单操作日志
     */
    public static OrderOperatonLog buildForConfirmOrder(long orderId, String operationName) {
        OrderOperatonLog orderOperatonLog = new OrderOperatonLog();
        orderOperatonLog.orderId = orderId;
        orderOperatonLog.type = "1";
        orderOperatonLog.operationName = operationName;
        orderOperatonLog.createTime = LocalDateTime.now();
        return orderOperatonLog;
    }
}
