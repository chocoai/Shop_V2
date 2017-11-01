package com.lecshop.sku.bean;

import com.lecshop.elasticsearch.bean.EsSkuImage;
import lombok.Data;

/**
 * Created by dujinkai on 17/5/13.
 * 单品图片
 */
@Data
public class SkuImage {
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
     * 图片地址
     */
    private String url;

    /**
     * 默认图片 0不是默认 1 默认图片
     */
    private String defaultFlag;

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

    /**
     * 获得es的单品图片
     *
     * @return 返回es的单品图片
     */
    public EsSkuImage convertEsSkuImage() {
        EsSkuImage esSkuImage = EsSkuImage.buildEmpty();
        esSkuImage.setUrl(this.url);
        return esSkuImage;
    }
}
