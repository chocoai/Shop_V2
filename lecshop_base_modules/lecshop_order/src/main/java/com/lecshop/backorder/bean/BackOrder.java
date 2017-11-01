package com.lecshop.backorder.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.order.bean.Order;
import com.lecshop.order.bean.OrderSku;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 17/6/6.
 * 退单实体
 */
@Data
public class BackOrder {

    /**
     * 主键id
     */
    private long id;

    /**
     * 退单号
     */
    private String backCode;

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 店铺id  平台的为0
     */
    private long storeId;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 退款或喝退货的单品ID多个用,分开
     */
    private String skuIds;

    /**
     * 1 退款 2 退货
     */
    private String type;

    /**
     * 退款／退货原因
     */
    private String reason;

    /**
     * 问题说明
     */
    private String desc;

    /**
     * 申请凭据 0 没有任何凭据 1 有发票 2有质检报告
     */
    private String credential;

    /**
     * 返回方式 1 快递返回 目前只有快递返回 （退货的时候用户给商城寄送商品）
     */
    private String backType;

    /**
     * 退款／退货金额
     */
    private BigDecimal backPrice;

    /**
     * 退货时候实际退款金额
     */
    private BigDecimal realBackPrice;

    /**
     * 上传的退款凭证或者质检发票 多个图片 用, 隔开
     */
    private String pics;

    /**
     * 是否预存款支付  0 否 1 是  默认0
     */
    private String predepositPay;

    /**
     * 退款／退货状态
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
     * 物流公司名称
     */
    private String logisCompanyName;

    /**
     * 物流单号
     */
    private String waybillCode;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 退单操作日志
     */
    private List<BackOrderLog> backOrderLogs;

    /**
     * 订单商品
     */
    private List<OrderSku> orderSkus;

}
