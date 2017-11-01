package com.lecshop.category.service;

import com.lecshop.category.bean.Category;

import java.util.List;

/**
 * Created by dujinkai on 17/5/11.
 * 分类接口
 */
public interface CategoryService {

    /**
     * 添加分类
     *
     * @param category 分类信息
     * @return 成功返回1  失败返回0
     */
    int addCategory(Category category);

    /**
     * 根据id查询分类信息
     *
     * @param id 分类id
     * @return 返回分类信息
     */
    Category queryCategoryById(long id);

    /**
     * 根据父级id 查询分类信息
     *
     * @param parentId 父级id
     * @return 返回指定父级下的所有分类
     */
    List<Category> queryCategoryByParentId(long parentId);


    /**
     * 删除分类
     *
     * @param category 分类
     * @return -1 有子分类不能删除  1 成功 0 失败
     */
    int deleteCategory(Category category);

    /**
     * 更新分类信息
     *
     * @param category 分类
     * @return 成功返回1 失败返回0
     */
    int updateCategory(Category category);

    /**
     * 查询所有一级分类和二级分类
     *
     * @return 分类集合
     */
    List<Category> queryAllFirstAndSecondCategory();


    /**
     * 根据店铺id查询签约三级分类
     *
     * @param storeId 店铺id
     * @return 签约分类集合
     */
    List<Category> queryThreeCategoryByStoreId(long storeId);

    /**
     * 根据店铺id查询签约的二级分类
     *
     * @param storeId 店铺id
     * @return 返回签约二级分类
     */
    List<Category> queryTwoCategoryByStoreId(long storeId);
}
