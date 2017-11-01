package com.lecshop.spu.mapper;

import com.lecshop.spu.bean.Spu;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/13.
 * 商品数据库接口
 */
public interface SpuMapper {

    /**
     * 添加商品
     *
     * @param spu 商品
     * @return 成功返回1  失败返回0
     */
    int addSpu(Spu spu);

    /**
     * 查询商品信息
     *
     * @param params 查询参数
     * @return 返回商品信息
     */
    Spu querySpu(Map<String, Object> params);

    /**
     * 查询商品总记录数
     *
     * @param params 查询参数
     * @return 返回商品总记录数
     */
    int querySpuCount(Map<String, Object> params);

    /**
     * 查询商品数据
     *
     * @param params 查询参数
     * @return 返回商品数据
     */
    List<Spu> querySpus(Map<String, Object> params);

    /**
     * 删除商品信息
     *
     * @param spu 商品信息
     * @return 成功发回1  失败返回0
     */
    int deleteSpu(Spu spu);

    /**
     * 更新商品信息
     *
     * @return 成功返回 1 失败返回0
     */
    int updateSpu(Spu spu);

    /**
     * 分页查询商品总数(关联单品查询)
     *
     * @param params 查询参数
     * @return 返回商品总数
     */
    int querySpusWithSkuCount(Map<String, Object> params);

    /**
     * 分页查询商品数据(关联单品查询)
     *
     * @param params 查询参数
     * @return 返回商品
     */
    List<Spu> querySpusWithSku(Map<String, Object> params);

    /**
     * 查询所有店铺待审核商品的总数
     *
     * @param params 查询 参数
     * @return 返回总数
     */
    int queryAllStoreToBeAuditdSpusCount(Map<String, Object> params);

    /**
     * 查询所有店铺待审核商品
     *
     * @param params 查询参数
     * @return 返回商品数据
     */
    List<Spu> queryAllStoreToBeAuditdSpus(Map<String, Object> params);

    /**
     * 查询所有店铺商品的总数
     *
     * @param params 查询参数
     * @return 返回店铺商品总数
     */
    int queryAllStoreSpusCount(Map<String, Object> params);


    /**
     * 查询所有店铺的商品
     *
     * @param params 查询参数
     * @return 返回店铺的所有商品
     */
    List<Spu> queryAllStoreSpus(Map<String, Object> params);
}
