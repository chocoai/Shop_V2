package com.lecshop.order.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/6/6.
 * 订单的一些附属信息实体
 */
@Data
public class OrderAttr {

    /**
     * 主键id
     */
    private long id;

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 收货人姓名
     */
    private String receiptName;

    /**
     * 收货人的地址 （省＋市＋区）
     */
    private String receiptAddress;

    /**
     * 收货人的详细地址
     */
    private String receiptDetailAddress;

    /**
     * 收货人的手机号码
     */
    private String receiptMobile;

    /**
     * 收货人的固定电话
     */
    private String receiptPhone;

    /**
     * 收货人的邮编
     */
    private String receiptZipCode;

    /**
     * 发票类型  0 不需要发票 1普通发票 默认0
     */
    private String invoiceType;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 订单备注
     */
    private String remark;
}
