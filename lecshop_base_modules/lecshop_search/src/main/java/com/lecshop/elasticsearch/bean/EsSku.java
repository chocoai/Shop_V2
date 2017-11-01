package com.lecshop.elasticsearch.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.elasticsearch.util.EsConstant;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 17/5/14.
 * 在es中的单品实体
 */
@Data
@Document(indexName = EsConstant.INDEX_NAME, type = EsConstant.DOCUMENT_TYPE)
public class EsSku implements Serializable {

    /***
     * 主键id
     */
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String skuId;

    /**
     * 商品id
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long spuId;

    /**
     * 单品名称
     */
    @Field(index = FieldIndex.analyzed, analyzer = "ik", type = FieldType.String, store = true)
    private String skuName;

    /**
     * 库存
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Integer)
    private int stock;

    /**
     * 价格
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Double)
    private BigDecimal price;

    /**
     * 店铺id 平台的为0
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long storeId;

    /**
     * 上架状态 0 下架  1上架
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String shelvesStatus;

    /**
     * 销量
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long saleNum;

    /**
     * 评论数
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long commentNum;

    /**
     * 品牌id
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long brandId;

    /**
     * 品牌名称
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String brandName;

    /**
     * 上架 时间
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime skuUpTime;

    /**
     * 平台一级分类id
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long fCateId;

    /**
     * 平台二级分类id
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long sCateId;

    /**
     * 平台三级分类id
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long tCateId;

    /**
     * 店铺一级分类
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long storeFCateId;

    /**
     * 店铺二级分类
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long storeSCateId;

    /**
     * 店铺三级分类id
     */
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Long)
    private long storeTCateId;

    /**
     * 单品图片
     */
    @Field(type = FieldType.Nested)
    private List<EsSkuImage> skuImages;

    /**
     * 单品的属性值信息
     */
    @Field(type = FieldType.Nested)
    private List<EsAttributeValue> attributeValues;


    /**
     * 构造空实体
     *
     * @return 返回空实体
     */
    public static EsSku buildEmpty() {
        return new EsSku();
    }

}
