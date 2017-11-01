package com.lecshop.spu.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.brand.bean.Brand;
import com.lecshop.category.bean.Category;
import com.lecshop.elasticsearch.bean.EsAttributeValue;
import com.lecshop.elasticsearch.bean.EsSku;
import com.lecshop.sku.bean.Sku;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import com.lecshop.util.CustomPriceDeserializer;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/13.
 * 商品
 */
@Data
public class Spu {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副标题
     */
    private String subTitle;

    /**
     * 销售价格
     */
    @JsonDeserialize(using = CustomPriceDeserializer.class)
    private BigDecimal price;

    /**
     * pc版的详情描述
     */
    private String pcDesc;

    /**
     * 手机版详情描述
     */
    private String mobileDesc;

    /**
     * seo标题
     */
    private String seoTitle;

    /**
     * seo关键字
     */
    private String seoKeywords;

    /**
     * seo描述
     */
    private String seoDesc;

    /**
     * 商品所属的店铺  平台的为0
     */
    private long storeId;

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
     * 类型id
     */
    private long typeId;

    /**
     * 品牌id
     */
    private long brandId;

    /**
     * 商品图片
     */
    private String url;


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
     * 商品下面的单品
     */
    private List<Sku> skus;

    /**
     * 商品的服务支持
     */
    private List<SpuServiceSupport> spuServiceSupports;

    /**
     * 商品规格值
     */
    private List<SpuSpecValue> spuSpecValues;

    /**
     * 商品图片
     */
    private List<SpuImage> spuImages;

    /**
     * 商品属性值
     */
    private List<SpuAttributeValue> spuAttributeValues;

    /**
     * 商品导入id
     */
    private long spuImportId;

    /**
     * 一级分类
     */
    private Category firstCategory;

    /**
     * 二级分类
     */
    private Category secondCategory;

    /**
     * 三级分类
     */
    private Category thirdCategory;

    /**
     * 品牌
     */
    private Brand brand;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺商品一级分类
     */
    private long storeFcateId;

    /**
     * 店铺商品二级分类
     */
    private long storeScateId;

    /**
     * 店铺商品三级分类
     */
    private long storeTcateId;

    /**
     * 判断是否是店铺的商品
     *
     * @return 是返回true  不是返回false  根据store是否是0 判断  0 是平台 不是0 是店铺
     */
    @JsonIgnore
    public boolean isStoreSpu() {
        return CommonConstant.ADMIN_STOREID != this.storeId;
    }

    /**
     * 设置新增商品的默认值
     *
     * @param name    操作人名称
     * @param storeId 店铺id
     * @return 返回商品
     */
    public Spu setDefaultValuesForAdd(String name, long storeId) {
        this.createName = name;
        this.createTime = LocalDateTime.now();
        this.delFlag = "0";
        this.storeId = storeId;
        setDefaultPic();
        return this;
    }

    /**
     * 设置默认图片
     */
    public void setDefaultPic() {
        if (!CollectionUtils.isEmpty(this.spuImages)) {
            this.url = this.spuImages.get(0).getUrl();
        }
    }

    /**
     * 设置单品的店铺id和审核状态
     */
    public void setSkuStoreIdAndAudit(boolean isNeedAudit) {
        if (!CollectionUtils.isEmpty(this.skus)) {
            skus.stream().forEach(sku -> {
                sku.setStoreId(this.storeId);
                sku.setAuditStatus(isNeedAudit);
            });
        }
    }

    /**
     * 设置和商品关联的商品id主要是(单品,服务支持,规格值)
     */
    public void setSpuLinkedSpuId() {

        // 设置单品的商品id
        if (!CollectionUtils.isEmpty(skus)) {
            skus.stream().forEach(sku -> sku.setSpuId(this.id));
        }

        // 设置商品服务支持的商品id
        if (!CollectionUtils.isEmpty(spuServiceSupports)) {
            spuServiceSupports.stream().forEach(spuServiceSupport -> spuServiceSupport.setSpuId(this.id));
        }

        // 设置商品规格值的商品id
        if (!CollectionUtils.isEmpty(spuSpecValues)) {
            spuSpecValues.stream().forEach(spuSpecValue -> spuSpecValue.setSpuId(this.id));
        }

        // 设置图片的商品id
        if (!CollectionUtils.isEmpty(spuImages)) {
            spuImages.stream().forEach(spuImage -> spuImage.setSpuId(this.id));
        }

        // 设置属性值的商品id
        if (!CollectionUtils.isEmpty(spuAttributeValues)) {
            spuAttributeValues.stream().forEach(spuAttributeValue -> spuAttributeValue.setSpuId(this.id));
        }
    }

    /**
     * 获得要比较的值
     *
     * @return 返回要比较的值
     */
    public String getUpdateString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(this.subTitle).append(this.price).append(this.brandId).append(this.pcDesc)
                .append(this.mobileDesc).append(this.seoTitle).append(this.seoDesc).append(this.seoKeywords);

        if (!CollectionUtils.isEmpty(this.spuServiceSupports)) {
            spuServiceSupports.stream().forEach(spuServiceSupport -> sb.append(spuServiceSupport.getServiceSupportId()));
        }

        if (!CollectionUtils.isEmpty(this.spuImages)) {
            spuImages.stream().forEach(spuImage -> sb.append(spuImage.getUrl()));
        }

        if (!CollectionUtils.isEmpty(this.spuAttributeValues)) {
            spuAttributeValues.stream().forEach(spuAttributeValue -> sb.append(spuAttributeValue.getAttributeValueId()));
        }

        return sb.toString();
    }

    /**
     * 判断商品是否发生了修改
     *
     * @param updateString 更新值
     * @return 发生变化 返回true  没有变化返回false
     */
    @JsonIgnore
    public boolean isModifySpu(String updateString) {
        return !getUpdateString().equals(updateString);
    }


    /**
     * 构造删除商品的实体
     *
     * @param name    操作人
     * @param id      商品id
     * @param storeId 店铺id
     * @return 返回商品信息
     */
    public static Spu buildForDelete(String name, long id, long storeId) {
        Spu spu = new Spu();
        spu.id = id;
        spu.delFlag = "1";
        spu.delName = name;
        spu.storeId = storeId;
        spu.delTime = LocalDateTime.now();
        return spu;
    }

    /**
     * 获得es的单品数据
     *
     * @return 返回es的单品数据
     */
    public List<EsSku> convertToEsSku() {

        if (CollectionUtils.isEmpty(this.skus)) {
            return Collections.emptyList();
        }

        return skus.stream().map(sku -> {
            EsSku esSku = sku.convertToEsSku();
            esSku.setBrandId(this.brandId);
            esSku.setBrandName(this.brand.getName());
            esSku.setFCateId(this.firstCateId);
            esSku.setSCateId(this.secondCateId);
            esSku.setTCateId(this.thirdCateId);
            esSku.setStoreFCateId(this.storeFcateId);
            esSku.setStoreSCateId(this.storeScateId);
            esSku.setStoreTCateId(this.storeTcateId);
            esSku.setSpuId(this.id);
            esSku.setAttributeValues(getEsAttributeValues());
            return esSku;
        }).collect(Collectors.toList());
    }

    /**
     * 获得es的属性值信息
     *
     * @return 返回es的属性值信息
     */
    private List<EsAttributeValue> getEsAttributeValues() {
        if (CollectionUtils.isEmpty(this.spuAttributeValues)) {
            return Collections.emptyList();
        }

        return this.spuAttributeValues.stream().map(SpuAttributeValue::convertEsAttributeValue).collect(Collectors.toList());
    }
}
