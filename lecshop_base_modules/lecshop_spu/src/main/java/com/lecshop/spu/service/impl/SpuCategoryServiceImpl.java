package com.lecshop.spu.service.impl;

import com.lecshop.spu.bean.SpuCategory;
import com.lecshop.spu.mapper.SpuCategoryMapper;
import com.lecshop.spu.service.SpuCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类service实现类
 *
 * Created by LecShop on 2017/6/15.
 */
@Service
public class SpuCategoryServiceImpl implements SpuCategoryService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpuCategoryServiceImpl.class);

    /**
     * 自动注入商品分类mapper
     */
    @Autowired
    private SpuCategoryMapper spuCategoryMapper;

    /**
     * 根据父级商品分类id查询商品分类
     * @param parentId  父级商品分类id
     * @param storeId   店铺id
     * @return          商品分类
     */
    public List<SpuCategory> querySpuCategoryByParentId(long parentId, long storeId) {
        logger.debug("querySpuCategoryByParentId and parentId :{} \r\n and storeId :{}", parentId, storeId);
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        params.put("storeId", storeId);
        return spuCategoryMapper.querySpuCategoryByParentId(params);
    }

    /**
     * 删除商品分类
     *
     * @param id      商品分类id
     * @param storeId 店铺id
     * @return -1 有子分类不能删除，  1 成功， 0 失败
     */
    @Override
    public int deleteSpuCategory(long id, long storeId) {
        logger.debug("deleteSpuCategory and id :{} \r\n and storeId :{}", id, storeId);
        // 判断该商品分类是否有子分类 如果有则不能删除
        if (hasChildren(id, storeId)) {
            logger.error("deleteSpuCategory fail due to SpuCategory has children...and SpuCategoryId : {}", id);
            return -1;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return spuCategoryMapper.deleteSpuCategory(params);
    }

    /**
     * 判断该商品分类下是否有子分类
     *
     * @param id 商品分类id
     * @return   有返回true，没有返回false
     */
    private boolean hasChildren(long id, long storeId) {
        logger.debug("hasChildren and id :{} \r\n and storeId :{}", id, storeId);
        return this.querySpuCategoryByParentId(id, storeId).size() > 0;
    }

    /**
     * 根据商品分类id及店铺id查询商品分类
     *
     * @param id      商品分类id
     * @param storeId 店铺id
     * @return        商品分类
     */
    @Override
    public SpuCategory querySpuCategoryById(long id, long storeId) {
        logger.debug("querySpuCategoryByParentId and id :{} \r\n and storeId :{}", id, storeId);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return spuCategoryMapper.querySpuCategoryById(params);
    }

    /**
     * 更新商品分类
     *
     * @param spuCategory 商品分类
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateSpuCategory(SpuCategory spuCategory) {
        logger.debug("updateSpuCategory and spuCategory :{}", spuCategory);
        return spuCategoryMapper.updateSpuCategory(spuCategory);
    }

    /**
     * 添加商品分类
     *
     * @param spuCategory 商品分类
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addSpuCategory(SpuCategory spuCategory) {
        logger.debug("addSpuCategory and spuCategory :{}", spuCategory);
        return spuCategoryMapper.addSpuCategory(spuCategory);
    }

}
