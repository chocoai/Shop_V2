package com.lecshop.category.mapper;

import com.lecshop.category.bean.StoreSignedCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/6/14.
 * 店铺签约分类数据库接口
 */
public interface StoreSignedCategoryMapper {

    /**
     * 查询店铺签约分类
     *
     * @param storeId 店铺id
     * @return 返回店铺签约分类
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
     * @param map 店铺id 分类id
     * @return 删除返回码
     */
    int deleteSingedCategoryById(Map<String, Object> map);
}
