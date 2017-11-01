package com.lecshop.sku.mapper;

import com.lecshop.sku.bean.SkuMemberPrice;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 单品会员价数据库接口
 */
public interface SkuMemberPriceMapper {

    /**
     * 新增单品会员价
     *
     * @param skuMemberPrices 单品会员价
     */
    void addSkuMemberPrices(List<SkuMemberPrice> skuMemberPrices);

    /**
     * 根据单品id查询单品会员价格
     *
     * @param skuId 单品id
     * @return 返回单品会员价格
     */
    List<SkuMemberPrice> queryBySkuId(String skuId);

    /**
     * 根据商品id删除单品会员价
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 根据商品id删除单品会员价(物理)
     *
     * @param spuId 商品id
     */
    void deleteBySpuIdPhysical(long spuId);

    /**
     * 根据会员等级id删除会员价格
     *
     * @param customerLevelId 会员等级id
     */
    void deleteByLevelId(long customerLevelId);

}
