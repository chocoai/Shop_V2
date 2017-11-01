package com.lecshop.category.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/6/14.
 * 店铺签约分类
 */
@Data
public class StoreSignedCategory {

    /**
     * 主键id
     */
    private long id;

    /**
     * 店铺id
     */
    private long storeId;

    /**
     * 分类id
     */
    private long cateId;

    /**
     * 分类信息
     */
    private Category category;

    /**
     * 添加签约分类封装实体类
     *
     * @param storeId
     * @param cateId
     * @return
     */
    public StoreSignedCategory getStoreSignedCategory(long storeId, long cateId) {
        this.storeId = storeId;
        this.cateId = cateId;
        return this;
    }
}
