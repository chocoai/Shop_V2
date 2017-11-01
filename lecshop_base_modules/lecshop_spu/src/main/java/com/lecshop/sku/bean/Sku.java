package com.lecshop.sku.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.elasticsearch.bean.EsSku;
import com.lecshop.elasticsearch.bean.EsSkuImage;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import com.lecshop.util.CustomPriceDeserializer;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/13.
 * 单品
 */
@Data
public class Sku {

    /**
     * 单品id
     */
    private String id;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 单品编号
     */
    private String skuNo;

    /**
     * 单品名称
     */
    private String name;

    /**
     * 单品副标题
     */
    private String subTitle;

    /**
     * 库存
     */
    private int stock;

    /**
     * 预警库存
     */
    private int warningStock = -1;

    /**
     * 销售价格
     */
    @JsonDeserialize(using = CustomPriceDeserializer.class)
    private BigDecimal price;

    /**
     * 重量
     */
    @JsonDeserialize(using = CustomPriceDeserializer.class)
    private BigDecimal weight;

    /**
     * 店铺id 平台的店铺id为0
     */
    private long storeId;

    /**
     * 上架状态  0下架 1上架 默认0
     */
    private String shelvesStatus;

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
     * 单品默认图片
     */
    private String url;

    /**
     * 审核状态  0 审核通过 1 审核未通过 2 审核中
     */
    private String status;

    /**
     * 上架时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime upTime = LocalDateTime.now();

    /**
     * 单品图片
     */
    private List<SkuImage> skuImages;

    /**
     * 单品会员价
     */
    private List<SkuMemberPrice> skuMemberPrices;

    /**
     * 单品规格值
     */
    private List<SkuSpecValue> skuSpecValues;

    /**
     * 设置和单品关联的单品id(主要是 单品图片 ,单品会员价格,单品规格值)
     */
    public void setSkuLinkedSkuIdAndSpuId() {
        // 设置单品图片的单品id
        if (!CollectionUtils.isEmpty(skuImages)) {
            skuImages.stream().forEach(skuImage -> skuImage.setSkuIdAndSpuId(this.id, this.spuId));
        }

        // 设置单品的会员价
        if (!CollectionUtils.isEmpty(skuMemberPrices)) {
            skuMemberPrices.stream().forEach(skuMemberPrice -> skuMemberPrice.setSkuIdAndSpuId(this.id, this.spuId));
        }

        // 设置单品的规格值
        if (!CollectionUtils.isEmpty(skuSpecValues)) {
            skuSpecValues.stream().forEach(skuSpecValue -> skuSpecValue.setSkuIdAndSpuId(this.id, this.spuId));
        }
    }

    /**
     * 设置默认图片
     */
    public void setDefaultPic() {
        if (!CollectionUtils.isEmpty(this.skuImages)) {
            this.url = this.skuImages.get(0).getUrl();
        }
    }

    /**
     * 设置审核的状态
     */
    public void setAuditStatus(boolean isNeedAudit) {
        // 平台的 或者审核开关关闭的 不需要审核 直接审核通过
        if (CommonConstant.ADMIN_STOREID == this.storeId || !isNeedAudit) {
            this.status = "0";
        } else {
            // 店铺的需要审核
            this.status = "2";
        }
    }


    /**
     * 设置自定义的主键id
     *
     * @param index 索引
     */
    public void setCustomId(int index) {
        if (!hasId()) {
            this.id = String.valueOf(System.currentTimeMillis()) + this.spuId + index;
        }
    }

    /**
     * 判断是否已经存在主键
     *
     * @return 存在返回true  不存在返回false
     */
    @JsonIgnore
    public boolean hasId() {
        return !StringUtils.isEmpty(this.id);
    }

    /**
     * 获得要比较的值
     *
     * @return 返回要比较的值
     */
    public String getUpdateString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.weight).append(this.price).append(this.stock).append(this.name).append(this.subTitle).append(this.skuNo).append(this.warningStock).append(this.shelvesStatus);

        if (!CollectionUtils.isEmpty(this.skuImages)) {
            skuImages.stream().forEach(skuImage -> sb.append(skuImage.getUrl()));
        }

        return sb.toString();
    }


    /**
     * 判断单品是否发生了修改
     *
     * @param updateString 更新值
     * @return 发生变化 返回true  没有变化返回false
     */
    @JsonIgnore
    public boolean isModifySku(String updateString) {
        return !getUpdateString().equals(updateString);
    }

    /**
     * 获得es的单品数据
     *
     * @return 返回es的单品数据
     */
    public EsSku convertToEsSku() {
        EsSku esSku = EsSku.buildEmpty();
        esSku.setSkuId(this.id);
        esSku.setSkuName(this.name);
        esSku.setStock(this.stock);
        esSku.setPrice(this.price);
        esSku.setStoreId(this.storeId);
        esSku.setShelvesStatus(this.shelvesStatus);
        esSku.setSaleNum(0);
        esSku.setCommentNum(0);
        esSku.setSkuUpTime(this.upTime);
        esSku.setSkuImages(getEsSkuImages());
        return esSku;
    }

    /**
     * 获得es的单品图片
     *
     * @return 返回es的单品图片
     */
    private List<EsSkuImage> getEsSkuImages() {
        if (CollectionUtils.isEmpty(skuImages)) {
            return Collections.emptyList();
        }

        return skuImages.stream().map(SkuImage::convertEsSkuImage).collect(Collectors.toList());
    }


    /**
     * 校验库存
     *
     * @param buyNum 需要购买的数量
     * @return 如果有库存返回true  没有库存返回false
     */
    public boolean validateStock(int buyNum) {
        return this.stock > 0 && this.stock >= buyNum;
    }

    /**
     * 校验单品是否是上架状态
     *
     * @return 如果是上架则返回true  不是返回false
     */
    public boolean validateStatus() {
        return "1".equals(this.shelvesStatus);
    }
}
