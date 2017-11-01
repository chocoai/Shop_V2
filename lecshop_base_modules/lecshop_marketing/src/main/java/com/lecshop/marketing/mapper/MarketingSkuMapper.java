package com.lecshop.marketing.mapper;

import com.lecshop.marketing.bean.MarketingSku;

import java.util.List;

/**
 * Created by dujinkai on 17/6/8.
 * 促销单品数据库
 */
public interface MarketingSkuMapper {

    /**
     * 新增促销单品信息
     *
     * @param marketingSkus 促销单品
     */
    void addMarketingSkus(List<MarketingSku> marketingSkus);

    /**
     * 根据促销id查询促销单品
     *
     * @param marketingId 促销id
     * @return 返回促销关联的促销单品
     */
    List<MarketingSku> queryMarketingSkusByMarketingId(long marketingId);

    /**
     * 根据促销id删除单品促销
     *
     * @param marketingId 促销id
     */
    void deleteByMarketingId(long marketingId);
}
