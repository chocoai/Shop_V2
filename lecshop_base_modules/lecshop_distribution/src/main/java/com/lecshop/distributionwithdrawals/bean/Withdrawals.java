package com.lecshop.distributionwithdrawals.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 提现列表实体类
 *
 * Created by LecShop on 2017/5/24.
 */
@Data
public class Withdrawals {

    /**
     * 主键id
     */
    private long id;

    /**
     * 交易流水号
     */
    private String tradeno;

    /**
     * 店铺id  平台的为0
     */
    private long storeId;

    /**
     * 会员id
     */
    private long customerid;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 状态 0 申请中 1审核通过  2提现完成 3审核不通过
     */
    private String status;

    /**
     * 拒绝原因
     */
    private String reason;

    /**
     * 申请日期
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime applytime;

    /**
     * 支付日期
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime paytime;

}
