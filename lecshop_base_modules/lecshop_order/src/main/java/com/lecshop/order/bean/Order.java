package com.lecshop.order.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 17/6/5.
 * 订单实体
 */
@Data
public class Order {

    /**
     * 订单id
     */
    private long id;

    /**
     * 订单code
     */
    private String orderCode;

    /**
     * 主订单code  下单可能会同时下多个订单 ，一个master_order_code 可以对应多个order_code 即多个订单
     */
    private String masterOrderCode;

    /**
     * 用户id
     */
    private long customerId;

    /**
     * 用户名称
     */
    private String customerName;

    /**
     * 订单的最终价格 (用户实际付款的金额)
     */
    private BigDecimal price;

    /**
     * 订单原始金额（商品优惠前的总价）
     */
    private BigDecimal originalPrice;

    /**
     * 运费
     */
    private BigDecimal freightPrice;

    /**
     * 商家修改的金额
     */
    private BigDecimal modifyPrice;

    /**
     * 积分抵消的金额
     */
    private BigDecimal pointPrice;

    /**
     * 优惠卷抵消的金额
     */
    private BigDecimal couponPrice;

    /**
     * 订单总的优惠价格 (包含商品参加的促销和订单使用的优惠卷,积分)
     */
    private BigDecimal concessionalRate;

    /**
     * 1:待付款  （用户刚下单）
     * 2:代发货  （用户付完款 等待商城发货）
     * 3:代收货  （商城已经发货 等待用户确认收货）
     * 4:已完成  （用户已经确认收货 订单结束）
     * 5:取消订单 （用户未付款前取消订单）
     * 6:退款通过  （用户已经付款但是商城还未发货，用户发出退款申请，商城同意退款）
     * 7:退货通过   （用户已经确认收货后用户发出退货申请，商城同意所有退货申请 ，一个订单可能有多个单品）
     */
    private String status;

    /**
     * 使用的优惠卷的卷码
     */
    private String couponNo;

    /**
     * 使用的积分数量
     */
    private String usePoint;

    /**
     * 支付类型  1在线支付  2货到付款
     */
    private String payType;

    /**
     * 订单店铺id  平台的订单id为0
     */
    private long storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 订单取消原因
     * 1:现在不想买
     * 2:商品价格较贵
     * 3:价格波动
     * 4:商品缺货
     * 5:重复下单
     * 6:收货人信息有误
     * 7:发票信息有误/发票未开
     * 8:送货时间过长
     * 9:其他原因
     */
    private String cancelReson;

    /**
     * 运费模版id
     */
    private long freightTemplateId;

    /**
     * 运单号
     */
    private String waybillCode;

    /**
     * 是否预存款支付  0 否 1 是  默认0
     */
    private String predepositPay;

    /**
     * 订单来源 1pc  2 h5   3 app
     */
    private String source;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime payTime;

    /**
     * 发货时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime deliveryTime;


    /**
     * 确认收货时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime receivingTime;

    /**
     * 订单取消时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime cancelTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 订单操作日志
     */
    private List<OrderOperatonLog> orderOperatonLogs;

    /**
     * 物流模版信息
     */
    private LogisticsTemplate logisticsTemplate;

    /**
     * 订单附属信息
     */
    private OrderAttr orderAttr;

    /**
     * 订单单品信息
     */
    private List<OrderSku> orderSkus;
}
