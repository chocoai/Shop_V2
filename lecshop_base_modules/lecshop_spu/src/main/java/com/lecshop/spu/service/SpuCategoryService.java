package com.lecshop.spu.service;

import com.lecshop.spu.bean.SpuCategory;

import java.util.List;
import java.util.Map;

/**
 * 商品分类service
 *
 * Created by LecShop on 2017/6/15.
 */
public interface SpuCategoryService {

    /**
     * 根据父级商品分类id查询商品分类
     * @param parentId  父级商品分类id
     * @param storeId  店铺id
     * @return    商品分类
     */
    List<SpuCategory> querySpuCategoryByParentId(long parentId, long storeId);

    /**
     * 添加商品分类
     *
     * @param spuCategory 商品分类
     * @return            成功返回1，失败返回0
     */
    int addSpuCategory(SpuCategory spuCategory);

    /**
     * 根据商品分类id及店铺id查询商品分类
     *
     * @param id 商品分类id
     * @param storeId 店铺id
     * @return 商品分类
     */
    SpuCategory querySpuCategoryById(long id, long storeId);

    /**
     * 更新商品分类
     *
     * @param spuCategory 商品分类
     * @return            成功返回1，失败返回0
     */
    int updateSpuCategory(SpuCategory spuCategory);

    /**
     * 删除商品分类
     *
     * @param id      商品分类id
     * @param storeId 店铺id
     * @return        -1 有子分类不能删除，  1 成功， 0 失败
     */
    int deleteSpuCategory(long id, long storeId);

}
