package com.lecshop.marketing.service.impl;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.mapper.MarketingMapper;
import com.lecshop.marketing.service.MarketingService;
import com.lecshop.marketing.service.MarketingSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by dujinkai on 17/6/7.
 * 促销模版
 */
public abstract class MarketingTemplate implements MarketingService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(MarketingTemplate.class);

    /**
     * 促销数据库接口
     */
    @Autowired
    private MarketingMapper marketingMapper;

    /**
     * 注入促销单品服务接口
     */
    @Autowired
    private MarketingSkuService marketingSkuService;


    @Transactional
    @Override
    public int addMarketing(Marketing marketing) {

        logger.debug("addMarketing and marketing:{}", marketing);

        if (Objects.isNull(marketing)) {
            logger.error("addMarketing fail due to marketing is null....");
            return 0;
        }

        // 新增促销主表
        marketingMapper.addMarketing(marketing);

        // 设置促销id
        marketing.setLinkedMarketingId();

        // 新增促销单品
        marketingSkuService.addMarketingSkus(marketing.getMarketingSkus());

        // 设置具体的促销详情
        return addMarketingDetail(marketing);
    }

    @Transactional
    @Override
    public int updateMarketing(Marketing marketing) {

        logger.debug("updateMarketing and marketing:{}", marketing);

        if (Objects.isNull(marketing)) {
            logger.error("updateMarketing fail due to marketing is null....");
            return 0;
        }

        // 更新促销主表
        if (marketingMapper.updateMarketing(marketing) != 1) {
            logger.error("updateMarketing fail...");
            return 0;
        }

        // 设置促销id
        marketing.setLinkedMarketingId();

        // 更新促销和单品的关系表
        marketingSkuService.updateMarketingSkus(marketing.getMarketingSkus(), marketing.getId());

        return updateMarketingDetail(marketing);
    }

    /**
     * 添加促销详情  由具体的促销来实现
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    public abstract int addMarketingDetail(Marketing marketing);

    /**
     * 更新促销详情  由具体的促销来实现
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    public abstract int updateMarketingDetail(Marketing marketing);
}
