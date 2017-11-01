package com.lecshop.sku.bean;

import com.lecshop.spec.bean.Spec;
import lombok.Data;

/**
 * Created by dujinkai on 17/5/13.
 * 单品的规格值
 */
@Data
public class SkuSpecValue {

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
     * 规格id
     */
    private long specId;

    /**
     * 规格值id
     */
    private String specValueId;

    /**
     * 规格值
     */
    private String valueRemark;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 规格信息
     */
    private Spec spec;

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
