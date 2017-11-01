package com.lecshop.backorder.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/6/6.
 * 退单操作日志
 */
@Data
public class BackOrderLog {

    /**
     * 主键id
     */
    private long id;

    /**
     * 退单id
     */
    private long backOrderId;

    /**
     * 操作人类型 1 用户  2 商家
     */
    private String operationType;

    /**
     * 留言
     */
    private String message;

    /**
     * 1:退款申请 （用户发送退款请求）
     * 2:退款成功（商家同意退款）
     * 3:退款拒绝 （商家拒绝退款）
     * 4:退货申请 （用户发起退货请求）
     * 5:退货拒绝   （商家拒绝退货）
     * 6:退货审核通过等待用户填写物流（商家审核通过，等待用户寄回商品）
     * 7: 待收货  （用户已经寄回商品，等待商家收货确认）
     * 8：退货完成（商家收货并且同意退款给用户）
     * 9:退货失败（商家不同意退款）
     */
    private String status;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;


    /**
     * 构造拒绝收货操作日志
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 返回拒绝收货的操作日志
     */
    public static BackOrderLog buildForRefuseToReceive(long backOrderId, String message) {
        BackOrderLog backOrderLog = new BackOrderLog();
        backOrderLog.backOrderId = backOrderId;
        backOrderLog.operationType = "2";
        backOrderLog.message = message;
        backOrderLog.status = "9";
        return backOrderLog;
    }

    /**
     * 构造同意确认收货的操作日志
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 返回同意确认收货的操作日志
     */
    public static BackOrderLog buildForConfirmReturn(long backOrderId, String message) {
        BackOrderLog backOrderLog = new BackOrderLog();
        backOrderLog.backOrderId = backOrderId;
        backOrderLog.operationType = "2";
        backOrderLog.message = message;
        backOrderLog.status = "8";
        return backOrderLog;
    }

    /**
     * 构造拒绝退货的操作日志
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 返回拒绝退货的操作日志
     */
    public static BackOrderLog buildForRefuseReturn(long backOrderId, String message) {
        BackOrderLog backOrderLog = new BackOrderLog();
        backOrderLog.backOrderId = backOrderId;
        backOrderLog.operationType = "2";
        backOrderLog.message = message;
        backOrderLog.status = "5";
        return backOrderLog;
    }

    /**
     * 构造同意退货的操作日志
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 返回同意退货的操作日志
     */
    public static BackOrderLog buildForAgreeToReturn(long backOrderId, String message) {
        BackOrderLog backOrderLog = new BackOrderLog();
        backOrderLog.backOrderId = backOrderId;
        backOrderLog.operationType = "2";
        backOrderLog.message = message;
        backOrderLog.status = "6";
        return backOrderLog;
    }

    /**
     * 构造同意退款的操作日志
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 返回同意退单的操作日志
     */
    public static BackOrderLog buildForAgreeToRefund(long backOrderId, String message) {
        BackOrderLog backOrderLog = new BackOrderLog();
        backOrderLog.backOrderId = backOrderId;
        backOrderLog.operationType = "2";
        backOrderLog.message = message;
        backOrderLog.status = "2";
        return backOrderLog;
    }

    /**
     * 构造拒绝退款的操作日志
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 返回拒绝退单的操作日志
     */
    public static BackOrderLog buildForRefuseToRefund(long backOrderId, String message) {
        BackOrderLog backOrderLog = new BackOrderLog();
        backOrderLog.backOrderId = backOrderId;
        backOrderLog.operationType = "2";
        backOrderLog.message = message;
        backOrderLog.status = "3";
        return backOrderLog;
    }
}
