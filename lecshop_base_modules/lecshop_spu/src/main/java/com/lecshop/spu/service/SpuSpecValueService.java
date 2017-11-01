package com.lecshop.spu.service;

import com.lecshop.spu.bean.SpuSpecValue;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 商品规格值服务接口
 */
public interface SpuSpecValueService {

    /**
     * 新增商品规格值
     *
     * @param spuSpecValues 商品规格值
     */
    void addSpuSpecValues(List<SpuSpecValue> spuSpecValues);

    /**
     * 根据商品id查询商品的规格值信息(附带规格返回)
     *
     * @param spuId 商品id
     * @return 返回商品的规格值信息
     */
    List<SpuSpecValue> queryBySpuIdWithSpec(long spuId);

    /**
     * 根据商品id删除商品规格值
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 更新商品规格值信息
     *
     * @param spuSpecValues 商品规格值信息
     * @param spuId         商品id
     */
    void updateSpuSpecValues(List<SpuSpecValue> spuSpecValues, long spuId);

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
