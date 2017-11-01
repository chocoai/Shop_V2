package com.lecshop.distributionset.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 分销设置实体类
 *
 * @author sunluyang on 2017/5/24.
 */
@Data
public class DistributionSet {
    /**
     * 主键id
     */
    private long id;
    /**
     * 店铺id  平台的为0
     */
    private long storeId;
    /**
     * 是否开启  0 为开启 1开启  默认为0
     */
    private String isOpen;
    /**
     * 一级佣金百分比 默认为0
     */
    private BigDecimal fcommission;
    /**
     * 二级佣金百分比 默认为0
     */
    private BigDecimal scommission;
    /**
     * 三级佣金 默认为0
     */
    private BigDecimal tcommission;

    /**
     * 开店完成后设置默认分销设置
     *
     * @param storeId 店铺id
     * @return 分销设置实体类
     */
    public DistributionSet getDefaultDistributionSet(long storeId) {
        this.storeId = storeId;
        this.isOpen = "1";
        this.fcommission = new BigDecimal(0.1);
        this.scommission = new BigDecimal(0.1);
        this.tcommission = new BigDecimal(0.1);
        return this;
    }

    /**
     * 组装编辑分销设置数据
     *
     * @param distributionSet 分销设置实体类数据
     * @param storeId         店铺id
     * @return 分销设置
     */
    public DistributionSet getEditDistributionSetData(DistributionSet distributionSet, long storeId) {
        distributionSet.setStoreId(storeId);
        return distributionSet;
    }
}
