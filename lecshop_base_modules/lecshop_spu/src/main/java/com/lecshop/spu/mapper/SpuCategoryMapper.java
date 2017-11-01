package com.lecshop.spu.mapper;

import com.lecshop.spu.bean.SpuCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商品分类mapper
 *
 * Created by LecShop on 2017/6/15.
 */
@Repository
public interface SpuCategoryMapper {

    /**
     * 根据父级商品分类id查询商品分类
     *
     * @param params  父级商品分类id及店铺id
     * @return        商品分类
     */
    List<SpuCategory> querySpuCategoryByParentId(Map<String, Object> params);

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
     * @param params 商品分类id及店铺id
     * @return       商品分类
     */
    SpuCategory querySpuCategoryById(Map<String, Object> params);

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
     * @param params 商品分类id及店铺id
     * @return       成功返回1，失败返回0
     */
    int deleteSpuCategory(Map<String, Object> params);

}
