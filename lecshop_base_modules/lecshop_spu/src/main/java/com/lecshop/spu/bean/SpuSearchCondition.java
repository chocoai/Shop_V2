package com.lecshop.spu.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/16.
 * 商品搜索的条件
 */
@Data
public class SpuSearchCondition {

    /**
     * 商品编号
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品品牌
     */
    private long brandId;

    /**
     * 一级分类id
     */
    private long firstCateId;

    /**
     * 二级分类id
     */
    private long secondCateId;

    /**
     * 三级分类id
     */
    private long thirdCateId;

    /**
     * 店铺id
     */
    private long storeId;

    /**
     * 单品的状态
     */
    private String status;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 获得搜索的参数
     *
     * @return 返回搜索参数
     */
    public Map<String, Object> getSearchMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("brandId", brandId);
        params.put("firstCateId", firstCateId);
        params.put("secondCateId", secondCateId);
        params.put("thirdCateId", thirdCateId);
        params.put("storeId", storeId);
        params.put("id", id);
        params.put("status", status);
        params.put("storeName", storeName);
        return params;
    }
}
