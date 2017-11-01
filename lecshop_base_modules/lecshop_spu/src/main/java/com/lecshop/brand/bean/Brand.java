package com.lecshop.brand.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/8.
 * 商品品牌
 */
@Data
public class Brand {

    /**
     * 主键id
     */
    private long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌别名
     */
    private String nickName;

    /**
     * 品牌的图片地址
     */
    private String url;

    /**
     * 证书图片
     */
    private String certificatUrl;

    /**
     * 店铺id 平台的为0
     */
    private long storeId;

    /**
     * 状态  0 申请中  1通过 2 拒绝
     */
    private String status;

    /**
     * 拒绝原因
     */
    private String reason;

    /**
     * 删除标记 0 未删除 1删除 默认0
     */
    private String delFlag = "0";

    /**
     * 创建者
     */
    private String createName;

    /**
     * 修改者
     */
    private String modifyName;

    /**
     * 删除者
     */
    private String delName;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 删除时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime delTime;

    /**
     * 品牌所属的店铺名称
     */
    private String storeName;

    /**
     * 设置新增品牌默认参数
     *
     * @param name    操作人
     * @param storeId 店铺id
     */
    public Brand setDefaultValuesForAdd(String name, long storeId) {
        this.createName = name;
        this.createTime = LocalDateTime.now();
        this.delFlag = "0";
        this.storeId = storeId;

        // 如果品牌为平台的则不需要审核直接通过
        if (0 == storeId) {
            this.status = "1";
        } else {
            this.status = "0";
        }

        return this;
    }

    /**
     * 设置修改品牌默认参数
     *
     * @param name    操作人
     * @param storeId 店铺id
     */
    public Brand setDefaultValuesForModify(String name, long storeId) {
        this.storeId = storeId;
        this.modifyName = name;
        this.modifyTime = LocalDateTime.now();
        return this;
    }

    /**
     * 构造删除品牌实体
     *
     * @param id      品牌id
     * @param delName 操作人
     * @param storeId 店铺id
     * @return 返回删除品牌实体
     */
    public static Brand buildDeleteBrand(long id, String delName, long storeId) {
        Brand brand = new Brand();
        brand.id = id;
        brand.delFlag = "1";
        brand.delName = delName;
        brand.delTime = LocalDateTime.now();
        brand.storeId = storeId;
        return brand;
    }

    public Brand addMySelfBrand(String name, String url, String certificatUrl, long storeId, String createName) {
        this.name = name;
        this.url = url;
        this.certificatUrl = certificatUrl;
        this.storeId = storeId;
        this.status = "0";
        this.delFlag = "0";
        this.createName = createName;
        this.createTime = LocalDateTime.now();
        return this;
    }
}
