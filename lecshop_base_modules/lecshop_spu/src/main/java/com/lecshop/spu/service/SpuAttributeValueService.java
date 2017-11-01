package com.lecshop.spu.service;

import com.lecshop.spu.bean.SpuAttributeValue;

import java.util.List;

/**
 * Created by dujinkai on 17/5/17.
 * 商品属性值服务接口
 */
public interface SpuAttributeValueService {

    /**
     * 新增商品属性值
     *
     * @param spuAttributeValues 商品属性值集合
     */
    void addSpuAttributeValues(List<SpuAttributeValue> spuAttributeValues);

    /**
     * 根据商品id删除商品属性
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 根据商品id查询商品属性值
     *
     * @param spuId 商品id
     * @return 返回商品属性值
     */
    List<SpuAttributeValue> queryBySpuId(long spuId);

    /**
     * 更新商品的属性值
     *
     * @param spuAttributeValues 商品属性值
     * @param spuId              商品id
     */
    void updateSpuAttributValues(List<SpuAttributeValue> spuAttributeValues, long spuId);
}
