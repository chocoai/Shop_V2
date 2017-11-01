package com.lecshop.spu.mapper;

import com.lecshop.spu.bean.SpuSpecValue;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 商品规格值数据库接口
 */
public interface SpuSpecValueMapper {

    /**
     * 新增商品规格值
     *
     * @param spuSpecValues 商品规格值
     */
    void addSpuSpecValues(List<SpuSpecValue> spuSpecValues);

    /**
     * 根据商品id 查询商品的规格值
     *
     * @param spuId 商品id
     * @return 返回商品的规格值信息
     */
    List<SpuSpecValue> queryBySpuId(long spuId);

    /**
     * 根据商品id删除商品规格值
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 根据商品id删除商品规格值(物理)
     *
     * @param spuId 商品id
     */
    void deleteBySpuIdPhysical(long spuId);

    /**
     * 根据规格id查询商品规格值记录数
     *
     * @param specId 规格id
     * @return 返回商品规格值记录数
     */
    int queryCountBySpecId(long specId);

    /**
     * 根据规格值id查询商品规格值记录数
     *
     * @param specValueId 规格值id
     * @return 返回商品规格值记录数
     */
    int queryCountBySpecValueId(String specValueId);
}
