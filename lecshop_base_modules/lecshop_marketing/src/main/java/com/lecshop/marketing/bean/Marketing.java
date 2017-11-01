package com.lecshop.marketing.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import com.lecshop.util.MarketingConstant;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 17/6/7.
 * 促销实体
 */
@Data
public class Marketing {

    /**
     * 促销id
     */
    private long id;

    /**
     * 促销名称
     */
    private String name;

    /**
     * 营销类型 1 直降 2团购 3抢购 4满减  5 满折 6 包邮
     */
    private String type;

    /**
     * 店铺id  平台的为0
     */
    private long storeId;

    /**
     * 营销描述
     */
    private String desc;

    /**
     * 删除标记  0 未删除 1 删除
     */
    private String delFlag;


    /**
     * 开始时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime endTime;

    /**
     * 团购详情
     */
    private GroupBuy groupBuy;

    /**
     * 抢购详情
     */
    private PanicBuy panicBuy;

    /**
     * 直降促销
     */
    private Fall fall;

    /**
     * 满减促销
     */
    private List<FullDown> fullDowns;

    /**
     * 满折促销
     */
    private List<FullDiscount> fullDiscounts;

    /**
     * 促销关联的单品信息
     */
    private List<MarketingSku> marketingSkus;

    /**
     * 包邮促销
     */
    private FreeShip freeShip;

    /**
     * 判断是否是团购
     *
     * @return 是返回true  不是返回false
     */
    @JsonIgnore
    public boolean isGroupBuyMarketing() {
        return MarketingConstant.GROUP_BUY_MARKETING.equals(this.type);
    }

    /**
     * 判断是否是抢购
     *
     * @return 是返回true  不是返回false
     */
    @JsonIgnore
    public boolean isPanicBuyMarketing() {
        return MarketingConstant.PANIC_BUY_MARKETING.equals(this.type);
    }

    /**
     * 判断是否是直降
     *
     * @return 是直降返回true  不是返回false
     */
    @JsonIgnore
    public boolean isFallMarketing() {
        return MarketingConstant.FALL_MARKETING.equals(this.type);
    }

    /**
     * 判断是否是满减
     *
     * @return 是满减返回true  不是返回false
     */
    @JsonIgnore
    public boolean isFullDownMarketing() {
        return MarketingConstant.FULL_DOWN_MARKETING.equals(this.type);
    }

    /**
     * 判断是否是满折
     *
     * @return 是满折返回true  不是返回false
     */
    @JsonIgnore
    public boolean isFullDiscountMarketing() {
        return MarketingConstant.FULL_DISCOUNT_MARKETING.equals(this.type);
    }

    /**
     * 判断是否是包邮促销
     *
     * @return 是包邮促销返回true  不是返回false
     */
    @JsonIgnore
    public boolean isFreeShipMarketing() {
        return MarketingConstant.FREE_SHIP_MARKETING.equals(this.type);
    }

    /**
     * 设置添加促销的默认值
     *
     * @param storeId 店铺id
     * @param type    促销类型
     * @return 返回促销信息
     */
    public Marketing setAddMarketingDefaultValues(long storeId, String type) {
        this.storeId = storeId;
        this.delFlag = "0";
        this.type = type;
        return this;
    }

    /**
     * 设置更新促销的默认值
     *
     * @param storeId 店铺id
     * @param type    促销类型
     * @return 返回促销信息
     */
    public Marketing setUpdateMarketingDefaultValues(long storeId, String type) {
        this.storeId = storeId;
        this.type = type;
        return this;
    }

    /**
     * 设置和促销相关的促销id
     */
    public void setLinkedMarketingId() {

        // 设置团购的促销id
        if (Objects.nonNull(this.groupBuy)) {
            groupBuy.setMarketingId(this.id);
        }

        // 设置抢购的促销id
        if (Objects.nonNull(this.panicBuy)) {
            panicBuy.setMarketingId(this.id);
        }

        // 设置直降的促销id
        if (Objects.nonNull(this.fall)) {
            fall.setMarketingId(this.id);
        }

        // 设置满减促销id
        if (!CollectionUtils.isEmpty(this.fullDowns)) {
            fullDowns.stream().forEach(fullDown -> fullDown.setMarketingId(this.id));
        }

        // 设置满折促销id
        if (!CollectionUtils.isEmpty(this.fullDiscounts)) {
            fullDiscounts.stream().forEach(fullDiscount -> fullDiscount.setMarketingId(this.id));
        }

        // 设置包邮促销id
        if (Objects.nonNull(this.freeShip)) {
            freeShip.setMarketingId(this.id);
        }

        // 设置促销单品的促销id
        if (!CollectionUtils.isEmpty(marketingSkus)) {
            marketingSkus.stream().forEach(marketingSku -> marketingSku.setMarketingId(this.id));
        }
    }
}
