package com.lecshop.brand.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/6/14.
 * 品牌申请
 */
@Data
public class BrandApply {

    /**
     * 主键id
     */
    private long id;

    /**
     * 店铺id
     */
    private long storeId;

    /**
     * 品牌id
     */
    private long brandId;

    /**
     * 申请状态   0 申请中 1 通过 2 拒绝
     */
    private String status;

    /**
     * 拒绝原因
     */
    private String reason;


    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 品牌信息
     */
    private Brand brand;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 获取品牌申请对象
     *
     * @param storeId 店铺id
     * @param brandId 品牌id
     * @return 品牌申请对象
     */
    public BrandApply getBrandApply(long storeId, long brandId) {
        this.storeId = storeId;
        this.brandId = brandId;
        return this;
    }
}
