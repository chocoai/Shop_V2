package com.lecshop.category.mapper;

import com.lecshop.category.bean.Category;

import java.util.List;

/**
 * Created by dujinkai on 17/5/11.
 * 分类数据库接口
 */
public interface CategoryMapper {

    /**
     * 新增分类
     *
     * @param category 分类信息
     * @return 成功返回1  失败返回0
     */
    int addCategory(Category category);

    /**
     * 查询分类信息
     *
     * @param id 分类id
     * @return 返回分类信息
     */
    Category queryCategoryById(long id);

    /**
     * 根据父级id查询分类信息
     *
     * @param parentId 父级id
     * @return 返回该父级分类下的所有分类信息
     */
    List<Category> queryCategoryByParentId(long parentId);


    /**
     * 删除分类
     *
     * @param category 分类
     * @return 成功返回 1 失败返回0
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
