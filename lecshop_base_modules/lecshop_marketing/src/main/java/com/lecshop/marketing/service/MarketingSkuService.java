package com.lecshop.marketing.service;

import com.lecshop.marketing.bean.MarketingSku;

import java.util.List;

/**
 * Created by dujinkai on 17/6/8.
 * 促销单品服务接口
 */
public interface MarketingSkuService {

    /**
     * 新增促销单品
     *
     * @param marketingSkus 促销单品
     */
    void addMarketingSkus(List<MarketingSku> marketingSkus);

    /**
     * 根据促销id查询促销单品
     *
     * @param marketingId 促销id
     * @param storeId     店铺id
     * @return 返回促销关联的促销单品
     */
    List<MarketingSku> queryMarketingSkusByMarketingId(long marketingId, long storeId);

    /**
     * 更新促销单品
     *
     * @param marketingSkus 促销单品
     * @param marketingId   促销id
     */
    void updateMarketingSkus(List<MarketingSku> marketingSkus, long marketingId);
}
