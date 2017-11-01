package com.lecshop.spu.mapper;

import com.lecshop.spu.bean.SpuImage;

import java.util.List;

/**
 * Created by dujinkai on 17/5/17.
 * 商品图片数据库接口
 */
public interface SpuImageMapper {

    /**
     * 添加商品图片
     *
     * @param spuImages 商品图片集合
     */
    void addSpuImages(List<SpuImage> spuImages);

    /**
     * 根据商品id删除商品图片
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 根据商品id删除商品图片(物理)
     *
     * @param spuId 商品id
     */
    void deleteBySpuIdPhysical(long spuId);

    /**
     * 根据商品id查询商品图片
     *
     * @param spuId 商品id
     * @return 返回商品图片
     */
    List<SpuImage> queryBySpuId(long spuId);
}
