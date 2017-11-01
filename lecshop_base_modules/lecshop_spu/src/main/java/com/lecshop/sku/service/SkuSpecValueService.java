package com.lecshop.sku.service;

import com.lecshop.sku.bean.SkuSpecValue;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 单品规格值服务接口
 */
public interface SkuSpecValueService {

    /**
     * 新增单品规格值
     *
     * @param skuSpecValues 单品规格值
     */
    void addSkuSpecValues(List<SkuSpecValue> skuSpecValues);

    /**
     * 根据单品id查询单品规格值
     *
     * @param skuId 单品id
     * @return 返回单品规格值
     */
    List<SkuSpecValue> queryBySkuId(String skuId);

    /**
     * 根据商品id删除单品规格值
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 根据商品id删除单品规格值 (物理删除)
     *
     * @param spuId 商品id
     */
    void deleteBySpuIdPhysical(long spuId);
}
