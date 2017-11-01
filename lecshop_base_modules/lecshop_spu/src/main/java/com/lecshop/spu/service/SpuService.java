package com.lecshop.spu.service;

import com.lecshop.spu.bean.Spu;
import com.lecshop.spu.bean.SpuSearchCondition;
import com.lecshop.util.PageHelper;

import java.util.List;


/**
 * Created by dujinkai on 17/5/13.
 * 商品服务接口
 */
public interface SpuService {

    /**
     * 新增商品
     *
     * @param spu 商品信息
     * @return 成功返回1  失败返回0
     */
    int addSpu(Spu spu);

    /**
     * 查询商品信息
     *
     * @param id 商品id
     * @param
     * @return 返回商品信息
     */
    Spu querySpu(long id, long storeId);

    /**
     * 分页查询商品信息(目前用在平台和店铺的商品列表查询)
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 商品搜索条件
     * @return 返回商品信息
     */
    PageHelper<Spu> querySpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition);


    /**
     * 分页查询商品信息 (该分页 商品关联单品表进行查询)(目前用在店铺端的待审核,审核通过,审核拒绝 商品列表查询)
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 搜索条件
     * @return 返回商品信息
     */
    PageHelper<Spu> querySpusWithSku(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition);

    /**
     * 查询所有店铺待审核的商品(该分页商关联单品和店铺进行查询 商品下只要有一个单品待审核 则就查出商品信息)(目前用在平台查询店铺待审核商品列表)
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 搜索条件
     * @return 返回所有店铺待审核的商品
     */
    PageHelper<Spu> queryAllStoreToBeAuditdSpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition);

    /**
     * 查询所有店铺的商品 (该分页商关联店铺进行查询) (目前用在平台查询店铺的商品)
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 搜索条件
     * @return 返回所有店铺的商品
     */
    PageHelper<Spu> queryAllStoreSpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition);

    /**
     * 删除商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1  失败返回0
     */
    int deleteSpu(Spu spu);

    /**
     * 批量删除商品信息
     *
     * @param spus 商品信息
     * @return 成功返回1 失败返回0
     */
    int deleteSpus(List<Spu> spus);

    /**
     * 根据商品id改变单品的上下架状态
     *
     * @param spuIds  商品id集合
     * @param status  上下架状态  0下架 1上架
     * @param storeId 店铺id
     * @return 成功返回>1  失败返回0
     */
    int updateShelvesStatus(List<Long> spuIds, String status, long storeId);

    /**
     * 更新商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1 失败返回0
     */
    int updateSpu(Spu spu);

    /**
     * 将商品数据存储到es中
     *
     * @param spuId 商品id
     */
    void indexEsSpu(long spuId);
}
