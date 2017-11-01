package com.lecshop.predeposit.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/23.
 * 预存款实体
 */
@Data
public class PredepositRecord {

    /**
     * 主键id
     */
    private long id;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 会员名称
     */
    private String customerName;

    /**
     * 交易类型  1:在线充值  2:订单消费  3:订单退款
     */
    private String transType;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 当前总金额
     */
    private BigDecimal currentMoney;

    /**
     * 备注
     */
    private String remark;

    /**
     * 充值支付状态 0 未支付 1 支付成功
     */
    private String status;

    /**
     * 交易编号
     */
    private String transCode;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

}
