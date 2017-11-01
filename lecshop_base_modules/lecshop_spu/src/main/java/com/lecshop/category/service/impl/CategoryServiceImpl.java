package com.lecshop.category.service.impl;

import com.lecshop.category.bean.Category;
import com.lecshop.category.mapper.CategoryMapper;
import com.lecshop.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/11.
 * 分类接口实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * 注入分类数据库接口
     */
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(Category category) {
        logger.debug("addCategory and category:{}", category);

        if (Objects.isNull(category)) {
            logger.error("addCategory fail due to category is empty....");
            return 0;
        }
        return categoryMapper.addCategory(category);
    }

    @Override
    public Category queryCategoryById(long id) {
        logger.debug("queryCategoryById and id :{}", id);

        return categoryMapper.queryCategoryById(id);
    }

    @Override
    public List<Category> queryCategoryByParentId(long parentId) {
        logger.debug("queryCategoryByParentId and parentId : {}", parentId);
        return categoryMapper.queryCategoryByParentId(parentId);
    }


    @Override
    public int deleteCategory(Category category) {
        logger.debug("deleteCategory and category :{}", category);

        // 判断该分类是否有子分类 如果有则不能删除
        if (hasChildren(category.getId())) {
            logger.error("deleteCategory fail due to category has children...and category : {}", category);
            return -1;
        }

        return categoryMapper.deleteCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        logger.debug("updateCategory and category : {}", category);
        if (Objects.isNull(category)) {
            logger.error("updateCategory fail due to category is null...");
            return 0;
        }
        return categoryMapper.updateCategory(category);
    }

    /**
     * 查询所有一级分类和二级分类
     *
     * @return 分类集合
     */
    @Override
    public List<Category> queryAllFirstAndSecondCategory() {
        List<Category> allList = categoryMapper.queryAllFirstAndSecondCategory();
        return allList.stream().filter(Category::isFirstCategory).map(category -> category.setChildCateGory(allList.stream().filter(childCategory -> category.getId() == childCategory.getParentId()).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    /**
     * 根据店铺id查询签约三级分类
     *
     * @param storeId 店铺id
     * @return 签约分类集合
     */
    @Override
    public List<Category> queryThreeCategoryByStoreId(long storeId) {
        return categoryMapper.queryThreeCategoryByStoreId(storeId);
    }

    /**
     * 根据店铺id查询签约的二级分类
     *
     * @param storeId 店铺id
     * @return 返回签约二级分类
     */
    @Override
    public List<Category> queryTwoCategoryByStoreId(long storeId) {
        return categoryMapper.queryTwoCategoryByStoreId(storeId);
    }

    /**
     * 判断分类下是否有子分类
     *
     * @param id 分类id
     * @return 有返回true  没有返回false
     */
    private boolean hasChildren(long id) {
        logger.debug("hasChildren and id :{}", id);
        return this.queryCategoryByParentId(id).size() > 0;
    }

}
