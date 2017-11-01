package com.lecshop.spu.service;

import com.lecshop.spu.bean.SpuServiceSupport;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 商品服务支持接口
 */
public interface SpuServiceSupportService {

    /**
     * 新增商品服务支持
     *
     * @param spuServiceSupports 商品服务支持
     */
    void addSpuServicceSupport(List<SpuServiceSupport> spuServiceSupports);

    /**
     * 根据商品id 查询商品的服务支持
     *
     * @param spuId 商品id
     * @return 返回商品的服务支持
     */
    List<SpuServiceSupport> queryBySpuId(long spuId);

    /**
     * 根据商品id删除商品服务支持
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 更新商品服务支持
     *
     * @param spuServiceSupports 商品服务支持
     * @param spuId              商品id
     */
    void updateSpuServiceSupport(List<SpuServiceSupport> spuServiceSupports, long spuId);
}
