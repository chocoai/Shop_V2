package com.lecshop.openstore.bean;

import com.lecshop.brand.bean.Brand;
import com.lecshop.category.bean.Category;
import lombok.Data;

import java.util.List;

/**
 * 开店信息实体类用于开店流程查询
 *
 * @author sunluyang on 2017/6/20.
 */
@Data
public class StoreBusinessInfo {

    /**
     * 公司信息
     */
    private StoreInfo storeInfo;
    /**
     * 二级分类
     */
    private List<Category> twoCategoryList;
    /**
     * 三级分类（签约分类）
     */
    private List<Category> threeCategoryList;
    /**
     * 主营店铺品牌
     */
    private List<Brand> storeBrandList;
    /**
     * 自定义品牌
     */
    private List<Brand> mySelfBrandList;

    public StoreBusinessInfo getStoreBusinessInfo(StoreInfo storeInfo, List<Category> twoCategoryList, List<Category> threeCategoryList, List<Brand> storeBrandList, List<Brand> mySelfBrandList) {
        this.storeInfo = storeInfo;
        this.twoCategoryList = twoCategoryList;
        this.threeCategoryList = threeCategoryList;
        this.storeBrandList = storeBrandList;
        this.mySelfBrandList = mySelfBrandList;
        return this;
    }

}
