package com.lecshop.sku.mapper;

import com.lecshop.sku.bean.Sku;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/13.
 * 单品数据库接口
 */
public interface SkuMapper {

    /**
     * 新增单品
     *
     * @param sku 单品信息
     */
    void addSku(Sku sku);

    /**
     * 根据商品id查询单品
     *
     * @param params 查询参数
     * @return 返回商品下面的单品
     */
    List<Sku> querySkuBySpuId(Map<String, Object> params);

    /**
     * 根据商品id删除单品信息
     *
     * @param params 查询参数
     */
    int deleteBySpuId(Map<String, Object> params);

    /**
     * 修改单品上下架状态
     *
     * @param params 参数
     * @return 成功返回 >1 失败返回0
     */
    int updateShelvesStatus(Map<String, Object> params);

    /**
     * 物理删除单品信息
     *
     * @param params 参数
     */
    int deleteBySpuIdPhysical(Map<String, Object> params);

    /**
     * 查询单品总记录数
     *
     * @param params 参数
     * @return 返回单品记录数
     */
    int querySkuCount(Map<String, Object> params);

    /**
     * 查询单品数据
     *
     * @param params 参数
     * @return 返回单品数据
     */
    List<Sku> querySkus(Map<String, Object> params);


    /**
     * 根据单品id 查询单品信息
     *
     * @param params 查询参数
     * @return 返回单品信息
     */
    Sku queryById(Map<String, Object> params);

    /**
     * 修改单品的审核状态
     *
     * @param params 参数
     * @return 成功返回>=1 失败返回0
     */
    int updateSkuAuditStatus(Map<String, Object> params);

    /**
     * 单品审核通过
     *
     * @param skuId 单品id
     * @return 成功返回1 失败返回0
     */
    int auditPass(String skuId);

    /**
     * 单品审核拒绝
     *
     * @param prams 参数
     * @return 成功返回1 失败返回0
     */
    int auditRefuse(Map<String, Object> prams);

    /**
     * 根据店铺id查询前几条单品数据
     *
     * @param storeId 店铺id
     * @return 单品集合
     */
    List<Sku> queryFiveDataForAttentionStore(long storeId);
}
