package com.lecshop.spu.mapper;

import com.lecshop.spu.bean.SpuAttributeValue;

import java.util.List;

/**
 * Created by dujinkai on 17/5/17.
 * 商品属性值数据库接口
 */
public interface SpuAttributeValueMapper {

    /**
     * 添加商品属性值
     *
     * @param spuAttributeValues 商品属性值集合
     */
    void addSpuAttributeValues(List<SpuAttributeValue> spuAttributeValues);

    /**
     * 根据商品id删除商品属性值
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);


    /**
     * 根据商品id删除商品属性值(物理)
     *
     * @param spuId 商品id
     */
    void deleteBySpuIdPhysical(long spuId);

    /**
     * 根据商品id查询商品属性值
     *
     * @param spuId 商品id
     * @return 返回商品属性值
     */
    List<SpuAttributeValue> queryBySpuId(long spuId);
}
