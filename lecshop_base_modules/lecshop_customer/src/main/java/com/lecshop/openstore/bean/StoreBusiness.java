package com.lecshop.openstore.bean;

import com.lecshop.brand.bean.Brand;
import lombok.Data;

import java.util.List;

/**
 * 店铺信息实体类用于开店流程添加
 *
 * @author sunluyang on 2017/6/19.
 */
@Data
public class StoreBusiness {
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 签约分类ids
     */
    private long[] categoryIds;
    /**
     * 主营品牌
     */
    private long[] brandIds;
    /**
     * 自定义品牌
     */
    private List<Brand> brands;
}
