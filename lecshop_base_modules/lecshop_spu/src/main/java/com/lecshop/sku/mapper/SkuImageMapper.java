package com.lecshop.sku.mapper;

import com.lecshop.sku.bean.SkuImage;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 单品图片数据库接口
 */
public interface SkuImageMapper {

    /**
     * 新增单品图片
     *
     * @param skuImages 单品图片
     */
    void addSkuImages(List<SkuImage> skuImages);

    /**
     * 根据单品id查询单品图片
     *
     * @param skuId 单品id
     * @return 返回单品图片
     */
    List<SkuImage> queryBySkuId(String skuId);

    /**
     * 根据商品id删除单品图片
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 根据商品id删除单品图片 (物理删除)
     *
     * @param spuId 商品id
     */
    void deleteBySpuIdPhysical(long spuId);
}
