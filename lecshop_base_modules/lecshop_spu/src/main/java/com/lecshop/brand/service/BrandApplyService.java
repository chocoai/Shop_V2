package com.lecshop.brand.service;

import com.lecshop.brand.bean.BrandApply;
import com.lecshop.util.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/14.
 * 品牌申请服务接口
 */
public interface BrandApplyService {

    /**
     * 搜索所有店铺审核通过的品牌
     *
     * @param storeId 店铺id
     * @return 返回店铺审核通过的所有品牌信息
     */
    List<BrandApply> queryAllPassBrand(long storeId);

    /**
     * 开店-添加店铺品牌
     *
     * @param list 品牌集合
     * @return 添加返回码
     */
    int addStoreBrand(List<BrandApply> list);

    /**
     * 处理添加品牌数据
     *
     * @param brandApply 实体类
     * @param storeId    店铺id
     * @return 添加返回码
     */
    int doAddStoreBrand(BrandApply brandApply, Long storeId);

    /**
     * 删除店铺品牌
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    int deleteStoreBrand(long storeId);

    /**
     * 根据店铺id和品牌id删除签约品牌
     *
     * @param storeId 删除条件
     * @param ids     品牌id
     * @return 删除返回码
     */
    int deleteStoreBrandByStoreIdAndBrandId(long storeId, long[] ids);

    /**
     * 分页查询待审核的品牌
     *
     * @param pageHelper 分页帮助类
     * @param name       品牌名称
     * @param storeName  店铺名称
     * @return 待审核的品牌
     */
    PageHelper<BrandApply> queryBrandToBeAudit(PageHelper<BrandApply> pageHelper, String name, String storeName);

    /**
     * 通过品牌审核
     *
     * @param id 品牌审核id
     * @return 成功返回1，失败返回0
     */
    int passBrandAudit(long id);

    /**
     * 批量通过品牌审核
     *
     * @param ids 品牌审核id数组
     * @return 成功返回>=1，失败返回0
     */
    int batchPassBrandAudit(long[] ids);

    /**
     * 拒绝品牌审核
     *
     * @param brandApply 商品审核实例
     * @return 成功返回1，失败返回0
     */
    int refuseBrandAudit(BrandApply brandApply);

    /**
     * 批量拒绝品牌审核
     *
     * @param ids 品牌审核id数组
     * @param reason 拒绝原因
     * @return 成功返回>=1，失败返回0
     */
    int batchRefuseBrandAudit(long[] ids, String reason);

    /**
     * 根据店铺id查询品牌并通过审核
     *
     * @param storeId 店铺id
     * @return  成功返回>=1，失败返回0
     */
    int passBrandAuditByStoreId(long storeId);
}
