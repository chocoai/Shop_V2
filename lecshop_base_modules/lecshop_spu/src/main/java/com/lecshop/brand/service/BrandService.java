package com.lecshop.brand.service;

import com.lecshop.brand.bean.Brand;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/5/8.
 * 品牌服务接口
 */
public interface BrandService {
    /**
     * 新增品牌
     *
     * @param brand 品牌
     * @return 返回码说明 0 失败 1成功
     */
    int addBrand(Brand brand);

    /**
     * 根据id查询品牌
     *
     * @param id      品牌id
     * @param storeId 店铺id
     * @return 返回品牌信息
     */
    Brand queryBrandById(long id, long storeId);

    /**
     * 更新品牌
     *
     * @param brand 品牌信息
     * @return 成功返回1 失败返回0
     */
    int updateBrand(Brand brand);

    /**
     * 删除商品品牌
     *
     * @param brand 商品品牌
     * @return 成功返回1  失败返回0
     */
    int deleteBrand(Brand brand);

    /**
     * 批量删除品牌
     *
     * @param brands 品牌信息
     * @return 成功返回1  失败返回0
     */
    int batchDeleteBrands(List<Brand> brands);

    /**
     * 分页查询品牌
     *
     * @param pageHelper 分页帮助类
     * @param name       品牌名称
     * @param nickName   品牌昵称
     * @param storeId
     * @return 返回品牌数据
     */
    PageHelper<Brand> queryBrands(PageHelper<Brand> pageHelper, String name, String nickName, long storeId);

    /**
     * 查询所有品牌(审核通过的)
     *
     * @param storeId 店铺id
     * @return 返回所有品牌
     */
    List<Brand> queryAllBrands(long storeId);

    /**
     * 查询所有admin品牌
     *
     * @return 返回admin品牌
     */
    List<Brand> queryAllAdminBrands();

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
     * @param storeId 店铺id
     * @return 返回所有品牌
     */
    List<Brand> queryStoreBrands(long storeId, String status);

    /**
     * 根据店铺id查询自定义品牌
     *
     * @param storeId 店铺id
     * @param status  状态
     * @return 返回所有品牌
     */
    List<Brand> queryCustomBrandByStoreIdAndStatus(long storeId, String status);


    /**
     * 分页查询自定义品牌
     *
     * @param pageHelper 分页帮助类
     * @param brandName  品牌名称
     * @param storeName  店铺名称
     * @return 自定义品牌信息
     */
    PageHelper<Brand> queryCustomBrandByStatus(PageHelper<Brand> pageHelper, String brandName, String storeName);

    /**
     * 通过自定义品牌审核
     *
     * @param id 自定义品牌id
     * @return 成功返回1，失败返回0
     */
    int passCustomBrandAudit(long id);

    /**
     * 拒绝自定义品牌审核
     *
     * @param brand 自定义品牌实例
     * @return 成功返回1，失败返回0
     */
    int refuseCustomBrandAudit(Brand brand);

    /**
     * 批量通过自定义品牌审核
     *
     * @param ids 自定义品牌id数组
     * @return 成功返回>=1，失败返回0
     */
    int batchPassCustomBrandAudit(long[] ids);

    /**
     * 批量拒绝自定义品牌审核
     *
     * @param ids    自定义品牌id数组
     * @param reason 拒绝原因
     * @return 成功返回>=1，失败返回0
     */
    int batchRefuseCustomBrandAudit(long[] ids, String reason);

    /**
     * 根据店铺id查找其自定义品牌并通过品牌审核
     *
     * @param storeId 店铺id
     * @return 成功返回>=1，失败返回0
     */
    int passCustomBrandByStoreId(long storeId);

    /**
     * 分页查询店铺下的所有品牌
     *
     * @param pageHelper 品牌分类
     * @param name       品牌名称
     * @return 分页数据
     */
    PageHelper queryStoreBrandsForPage(PageHelper<Brand> pageHelper, long storeId, String name);

}
