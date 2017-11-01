package com.lecshop.sku.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/5/13.
 * 单品会员价格
 */
@Data
public class SkuMemberPrice {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 单品id
     */
    private String skuId;

    /**
     * 会员等级id
     */
    private long memberLevelId;

    /**
     * 会员价格
     */
    private BigDecimal price;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 设置单品id和商品id
     *
     * @param skuId 单品id
     * @param spuId 商品id
     */
    public void setSkuIdAndSpuId(String skuId, long spuId) {
        this.skuId = skuId;
        this.spuId = spuId;
    }

}
