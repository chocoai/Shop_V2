package com.lecshop.attention.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.sku.bean.Sku;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 关注店铺实体类
 *
 * @author sunluyang on 2017/7/4.
 */
@Data
public class AttentionStore {
    /**
     * 主键id
     */
    private long id;
    /**
     * 会员id
     */
    private long customerId;
    /**
     * 店铺id
     */
    private long storeId;
    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 店铺信息
     */
    private StoreInfo storeInfo;

    /**
     * 商品集合
     */
    private List<Sku> skuList;

    /**
     * 关注店铺数量
     */
    private int storeAttentionCount;

    /**
     * 获取店铺关注
     *
     * @param storeInfo 店铺信息
     * @param skuList   单品集合
     * @return 返回店铺关注
     */
    public AttentionStore getAttentionStore(StoreInfo storeInfo, List<Sku> skuList, int storeAttentionCount) {
        this.setStoreInfo(storeInfo);
        this.setSkuList(skuList);
        this.setStoreAttentionCount(storeAttentionCount);
        return this;
    }
}
