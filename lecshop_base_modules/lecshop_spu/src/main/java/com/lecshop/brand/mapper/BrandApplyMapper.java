package com.lecshop.brand.mapper;

import com.lecshop.brand.bean.BrandApply;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/14.
 * 品牌申请数据库接口
 */
public interface BrandApplyMapper {

    /**
     * 查询店铺所有审核通过的品牌
     *
     * @param storeId 店铺id
     * @return 返回店铺所有审核通过的品牌
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
     * 根据店铺id和品牌id查询签约品牌信息
     *
     * @param map 查询条件
     * @return
     */
    BrandApply queryStoreBrandByStoreIdAndBrandId(Map<String, Object> map);

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
     * @param map 删除条件
     * @return 删除返回码
     */
    int deleteStoreBrandByStoreIdAndBrandId(Map<String, Object> map);

    /**
     * 分页查询所有待审核的品牌
     *
     * @param params 查询参数
     * @return 待审核的品牌
     */
    List<BrandApply> queryBrandToBeAudit(Map<String, Object> params);

    /**
     * 查询待审核品牌的总记录数
     *
     * @param params 查询参数
     * @return 总记录数
     */
    int queryBrandToBeAuditCount(Map<String, Object> params);

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
     * @param params 品牌审核id数组及拒绝原因
     * @return 成功返回>=1，失败返回0
     */
    int batchRefuseBrandAudit(Map<String, Object> params);

    /**
     * 根据店铺id查询品牌并通过审核
     *
     * @param storeId 店铺id
     * @return  成功返回>=1，失败返回0
     */
    int passBrandAuditByStoreId(long storeId);
}
