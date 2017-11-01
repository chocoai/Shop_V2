package com.lecshop.brand.mapper;

import com.lecshop.brand.bean.Brand;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/8.
 * 品牌数据库接口
 */

public interface BrandMapper {

    /**
     * 新增品牌
     *
     * @param brand 品牌
     * @return 返回0 失败 1 成功
     */
    int addBrand(Brand brand);

    /**
     * 根据品牌查询品牌id
     *
     * @param params 查询参数
     * @return 返回品牌信息
     */
    Brand queryBrandById(Map<String, Object> params);

    /**
     * 更新品牌
     *
     * @param brand 品牌信息
     * @return 成功返回1  失败返回0
     */
    int updateBrand(Brand brand);

    /**
     * 删除品牌信息
     *
     * @param brand 品牌信息
     * @return 成功返回1  失败返回0
     */
    int deleteBrand(Brand brand);

    /**
     * 分页查询品牌数据
     *
     * @param params 查询参数
     * @return 返回品牌数据
     */
    List<Brand> queryBrands(Map<String, Object> params);

    /**
     * 查询品牌的总记录数
     *
     * @param params 查询参数
     * @return 返回总记录数
     */
    int queryBrandsCount(Map<String, Object> params);

    /**
     * 查询所有品牌
     *
     * @param storeId
     * @return 返回所有品牌
     */
    List<Brand> queryAllBrands(long storeId);

    /**
     * 添加自定义品牌
     *
     * @param list 品牌集合
     * @return 添加返回码
     */
    int batchAddCustomBrand(List<Brand> list);

    /**
     * 删除自定义品牌
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    int batchDeleteCustomBrand(long storeId);

    /**
     * 根据店铺id查询主营品牌
     *
     * @param brand 品牌信息
     * @return 返回所有品牌
     */
    List<Brand> queryStoreBrands(Brand brand);

    /**
     * 查询店铺下的所有签约品牌总数
     *
     * @param map 查询条件
     * @return 返回个数
     */
    int queryStoreBrandsForPageCount(Map<String, Object> map);

    /**
     * 查询店铺下的所有品牌
     *
     * @param map 查询条件
     * @return 返回个数
     */
    List<Brand> queryStoreBrandsForPage(Map<String, Object> map);

    /**
     * 根据店铺id和状态查询自定义品牌
     *
     * @param brand 品牌信息
     * @return 返回所有品牌
     */
    List<Brand> queryCustomBrandByStoreIdAndStatus(Brand brand);


    /**
     * 分页查询自定义品牌
     *
     * @param params 品牌名称及店铺名称
     * @return 返回自定义品牌数据
     */
    List<Brand> queryCustomBrandByStatus(Map<String, Object> params);

    /**
     * 查询自定义品牌总记录数
     *
     * @param params 品牌名称及店铺名称
     * @return 自定义品牌总记录数
     */
    int queryCustomBrandCount(Map<String, Object> params);

    /**
     * 通过自定义品牌审核
     *
     * @param id 自定义品牌id
     * @return 成功返回1，失败返回0
     */
    int passCustomBrandAudit(long id);

    /**
     * 批量通过自定义品牌审核
     *
     * @param ids 自定义品牌id数组
     * @return 成功返回>=1，失败返回0
     */
    int batchPassCustomBrandAudit(long[] ids);

    /**
     * 拒绝自定义品牌审核
     *
     * @param brand 自定义品牌实例
     * @return 成功返回1，失败返回0
     */
    int refuseCustomBrandAudit(Brand brand);

    /**
     * 批量拒绝自定义品牌审核
     *
     * @param params 自定义品牌id数组及拒绝原因
     * @return 成功返回>=1，失败返回0
     */
    int batchRefuseCustomBrandAudit(Map<String, Object> params);

    /**
     * 根据店铺id查找其自定义品牌并通过品牌审核
     *
     * @param storeId 店铺id
     * @return 成功返回>=1，失败返回0
     */
    int passCustomBrandByStoreId(long storeId);
}
