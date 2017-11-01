package com.lecshop.spu.mapper;

import com.lecshop.spu.bean.SpuServiceSupport;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 商品服务支持 数据库接口
 */
public interface SpuServicceSupportServiceMapper {

    /**
     * 新增商品服务支持
     *
     * @param spuServiceSupports 商品服务支持
     */
    void addSpuServicceSupportServices(List<SpuServiceSupport> spuServiceSupports);

    /**
     * 根据商品id查询商品服务支持
     *
     * @param spuId 商品id
     * @return 返回商品服务支持
     */
    List<SpuServiceSupport> queryBySpuId(long spuId);

    /**
     * 根据商品id删除商品服务支持
     *
     * @param spuId 商品id
     */
    void deleteBySpuId(long spuId);

    /**
     * 根据商品id删除商品服务支持(物理)
     *
     * @param spuId 商品id
     */
    void deleteBySpuIdPhysical(long spuId);

}
