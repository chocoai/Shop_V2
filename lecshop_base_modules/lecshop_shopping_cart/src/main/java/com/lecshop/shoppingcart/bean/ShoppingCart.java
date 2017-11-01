package com.lecshop.shoppingcart.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/7/5.
 * 购物车实体
 */
@Data
public class ShoppingCart {
    /**
     * 主键id
     */
    private long id;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 单品id
     */
    private String skuId;

    /**
     * 数量
     */
    private int num;

    /**
     * 使用的优惠id(主要是满减,满折的id)
     */
    private long marketingId;

    /**
     * 删除标记
     */
    private String delFlag = "0";

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 设置新增购物车时候的默认参数
     *
     * @param customerId 会员id
     * @return 返回购物车
     */
    public ShoppingCart setDefaultValuesForAdd(long customerId) {
        this.customerId = customerId;
        this.delFlag = "0";
        this.createTime = LocalDateTime.now();
        return this;
    }

    /**
     * 校验购物车参数是否正确
     *
     * @return 正确返回 true  不正确返回false
     */
    public boolean validate() {
        return customerId != 0 && num > 0 && !StringUtils.isEmpty(skuId);
    }

    /**
     * 未登录状态夏校验购物车参数是否正确
     *
     * @return 正确返回true  不正确返回fasle
     */
    public boolean validateWithNoLogin() {
        return num > 0 && !StringUtils.isEmpty(skuId);
    }
}
