package com.lecshop.marketingmanager.bean;

import com.lecshop.sku.bean.Sku;
import lombok.Data;

/**
 * 商品组合与单品的关联实体类
 *
 * Created by LecShop on 2017/6/13.
 */
@Data
public class CombinationSku {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品组合id
     */
    private long combinationId;

    /**
     * 单品id
     */
    private String skuId;

    /**
     * 单品信息
     */
    private Sku sku;

}
