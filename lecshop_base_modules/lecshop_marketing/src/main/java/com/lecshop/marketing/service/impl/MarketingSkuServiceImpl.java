package com.lecshop.marketing.service.impl;

import com.lecshop.marketing.bean.MarketingSku;
import com.lecshop.marketing.mapper.MarketingSkuMapper;
import com.lecshop.marketing.service.MarketingSkuService;
import com.lecshop.sku.service.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/6/8.
 * 促销单品服务接口实现
 */
@Service
public class MarketingSkuServiceImpl implements MarketingSkuService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(MarketingSkuServiceImpl.class);


    /**
     * 注入促销单品信息
     */
    @Autowired
    private MarketingSkuMapper marketingSkuMapper;

    /**
     * 注入单品服务接口
     */
    @Autowired
    private SkuService skuService;

    @Override
    public void addMarketingSkus(List<MarketingSku> marketingSkus) {
        logger.debug("addMarketingSkus and marketingSkus:{}", marketingSkus);

        if (CollectionUtils.isEmpty(marketingSkus)) {
            logger.warn("not need to addMarketingSkus ");
            return;
        }

        marketingSkuMapper.addMarketingSkus(marketingSkus);
    }

    @Override
    public List<MarketingSku> queryMarketingSkusByMarketingId(long marketingId, long storeId) {
        logger.debug("queryMarketingSkusByMarketingId and marketingId:{} \r\n storeId:{}", marketingId, storeId);
        return marketingSkuMapper.queryMarketingSkusByMarketingId(marketingId).stream().map(marketingSku -> {
            marketingSku.setSku(skuService.querySkuByIdWithSpecs(marketingSku.getSkuId(), storeId));
            return marketingSku;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateMarketingSkus(List<MarketingSku> marketingSkus, long marketingId) {
        //删除单品促销
        marketingSkuMapper.deleteByMarketingId(marketingId);

        // 新增单品促销
        addMarketingSkus(marketingSkus);
    }
}
