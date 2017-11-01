package com.lecshop.category.service.impl;

import com.lecshop.category.bean.StoreSignedCategory;
import com.lecshop.category.mapper.StoreSignedCategoryMapper;
import com.lecshop.category.service.StoreSignedCategoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dujinkai on 17/6/14.
 * 店铺签约分类实现接口
 */
@Service
public class StoreSignedCategoryServiceImpl implements StoreSignedCategoryService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(StoreSignedCategoryServiceImpl.class);

    /**
     * 注入店铺签约分类
     */
    @Autowired
    private StoreSignedCategoryMapper storeSignedCategoryMapper;

    @Override
    public List<StoreSignedCategory> queryAllSignedCategorys(long storeId) {
        logger.debug("queryAllSignedCategorys and storeId:{}", storeId);
        return storeSignedCategoryMapper.queryAllSignedCategorys(storeId);
    }

    /**
     * 批量添加签约分类
     *
     * @param list 店铺签约分类集合
     * @return 添加返回码
     */
    @Override
    public int addSignedCategory(List<StoreSignedCategory> list) {
        logger.debug("addSignedCategory and list:{}", list);
        return storeSignedCategoryMapper.addSignedCategory(list);
    }

    /**
     * 删除店铺签约分类
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    @Override
    public int deleteSignedCategory(long storeId) {
        logger.debug("deleteSignedCategory and storeId:{}", storeId);
        return storeSignedCategoryMapper.deleteSignedCategory(storeId);
    }

    /**
     * 根据店铺id和分类id删除签约分类
     *
     * @param storeId 店铺id
     * @param cateId  分类id
     * @return 删除返回码
     */
    @Override
    public int deleteSingedCategoryById(long storeId, long cateId) {
        logger.debug("deleteSingedCategoryById and storeId:{}\r\n cateId:{}", storeId, cateId);
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("cateId", cateId);
        return storeSignedCategoryMapper.deleteSingedCategoryById(map);
    }

    /**
     * 添加签约分类admin端用
     *
     * @param categoryIds 分类id数组
     * @param storeId     店铺id
     * @return 返回添加码
     */
    @Override
    public int addSignedCategoryAdmin(long[] categoryIds, long storeId) {
        logger.debug("addSignedCategoryAdmin and storeId:{}\r\n categoryIds:{}", storeId, categoryIds);
        if (ArrayUtils.isEmpty(categoryIds)) {
            logger.error("addSignedCategoryAdmin error due to categoryIds is empty");
            return -1;
        }
        List<Long> categoryIdsList = Arrays.asList(ArrayUtils.toObject(categoryIds));
        List<Long> filterList = new ArrayList<>();
        queryAllSignedCategorys(storeId).forEach(storeSignedCategory -> {
            if (categoryIdsList.contains(storeSignedCategory.getCateId())) {
                filterList.add(storeSignedCategory.getCateId());
            }
        });
        List<Long> toRemoveList = new ArrayList<>();
        toRemoveList.addAll(categoryIdsList);
        toRemoveList.removeAll(filterList);
        if (CollectionUtils.isEmpty(toRemoveList)) {
            logger.error("addSignedCategoryAdmin error due to already add");
            return -2;
        }
        //添加签约分类
        List<StoreSignedCategory> storeSignedCategories = new ArrayList<>();
        toRemoveList.forEach(categoryId -> storeSignedCategories.add(new StoreSignedCategory().getStoreSignedCategory(storeId, (long) categoryId)));
        return addSignedCategory(storeSignedCategories);
    }
}
