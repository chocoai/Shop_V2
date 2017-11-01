package com.lecshop.sku.service;

import com.lecshop.sku.bean.Sku;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 单品服务接口
 */
public interface SkuService {

    /**
     * 添加单品
     *
     * @param skus 单品信息
     */
    void addSkus(List<Sku> skus);

    /**
     * 根据商品id查询单品信息
     *
     * @param spuId   商品id
     * @param storeId
     * @return 返回单品信息
     */
    List<Sku> querySkuBySpuId(long spuId, long storeId);

    /**
     * 根据商品id删除单品信息
     *
     * @param spuId   商品id
     * @param storeId 店铺id
     */
    void deleteBySpuId(long spuId, long storeId);

    /**
     * 根据商品id修改单品上下架状态
     *
     * @param spuIds  商品id
     * @param status  上下架状态
     * @param storeId 店铺id
     * @return 成功返回>1 失败返回0
     */
    int updateShelvesStatus(List<Long> spuIds, String status, long storeId);

    /**
     * 更新单品信息
     *
     * @param skus    单品集合
     * @param spuId   商品id
     * @param storeId 店铺id
     */
    void updateSkus(List<Sku> skus, long spuId, long storeId);

    /**
     * 根据单品id查询单品信息
     *
     * @param skuId   单品id
     * @param storeId 店铺id
     * @return 返回单品信息(包括单品的规格信息)
     */
    Sku querySkuByIdWithSpecs(String skuId, long storeId);

    /**
     * 分页查询单品信息
     *
     * @param pageHelper 分页帮助类
     * @param storeId    店铺id
     * @param name       单品名称
     * @param skuNo      单品编号
     * @return 返回单品信息 (包括单品的规格信息)
     */
    PageHelper<Sku> querySkusWithSpecs(PageHelper<Sku> pageHelper, long storeId, String name, String skuNo);

    /**
     * 将单品修改成待审核状态
     *
     * @param spuIds  商品id集合
     * @param storeId 店铺id
     * @return 成功返回>=1 失败返回0
     */
    int updateSkuToAudit(List<Long> spuIds, long storeId);


    /**
     * 单品审核通过
     *
     * @param skuIds 单品id集合
     * @return 成功返回1 失败返回0
     */
    int auditPass(String[] skuIds);

    /**
     * 单品审核拒绝
     *
     * @param skuIds 单品id 集合
     * @param reason 拒绝原因
     * @return 成功返回1 失败返回0
     */
    int auditRefuse(String[] skuIds, String reason);


    /**
     * 根据店铺id查询前几条单品数据
     *
     * @param storeId 店铺id
     * @return 单品集合
     */
    List<Sku> queryFiveDataForAttentionStore(long storeId);

    /**
     * 根据单品id查询单品信息
     *
     * @param skuId 单品id
     * @return 返回单品信息(只有单品信息)
     */
    Sku querySkuById(String skuId);

}
