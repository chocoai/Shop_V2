package com.lecshop.attention.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.sku.bean.Sku;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/27.
 * 商品关注
 */
@Data
public class Attention {

    /**
     * 主键id
     */
    private long id;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 单品id
     */
    private long skuId;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;


    /**
     * 删除时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime delTime;

    /**
     * 单品信息
     */
    private Sku sku;

    /**
     * 关注单品的评价数量
     */
    private int commentCount;

    /**
     * 获取商品关注实体类
     *
     * @param skuId      单品id
     * @param customerId 会员id
     * @return 商品关注实体类
     */
    public Attention getAttentionForCancelAttention(String skuId, long customerId) {
        this.skuId = Long.parseLong(skuId);
        this.customerId = customerId;
        return this;
    }
}
