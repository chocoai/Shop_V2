package com.lecshop.category.service;

import com.lecshop.category.bean.StoreSignedCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/14.
 * 签约分类服务接口
 */
public interface StoreSignedCategoryService {

    /**
     * 查询店铺的所有签约分类
     *
     * @param storeId 店铺id
     * @return 返回店铺所有分类
     */
    List<StoreSignedCategory> queryAllSignedCategorys(long storeId);

    /**
     * 批量插入店铺签约分类
     *
     * @param list 店铺签约分类集合
     * @return 添加返回码
     */
    int addSignedCategory(List<StoreSignedCategory> list);

    /**
     * 删除店铺签约分类
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    int deleteSignedCategory(long storeId);

    /**
     * 根据店铺id和分类id删除签约分类
     *
     * @param storeId 店铺id
     * @param cateId  分类id
     * @return 删除返回码
     */
    int deleteSingedCategoryById(long storeId, long cateId);

    /**
     * 添加签约分类admin端用
     *
     * @param categoryIds 分类id数组
     * @param storeId     店铺id
     * @return 返回添加码
     */
    int addSignedCategoryAdmin(long[] categoryIds, long storeId);
}
