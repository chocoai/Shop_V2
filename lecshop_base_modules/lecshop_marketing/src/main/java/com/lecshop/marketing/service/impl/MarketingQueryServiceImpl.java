package com.lecshop.marketing.service.impl;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.mapper.MarketingMapper;
import com.lecshop.marketing.service.MarketingQueryService;
import com.lecshop.marketing.service.MarketingService;
import com.lecshop.marketing.service.MarketingSkuService;
import com.lecshop.util.MarketingConstant;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/6/8.
 * 促销查询实现接口
 */
@Service
public class MarketingQueryServiceImpl implements MarketingQueryService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(MarketingQueryServiceImpl.class);

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

    /**
     * 注入团购服务接口
     */
    @Resource(name = "groupBuyService")
    private MarketingService groupBuyService;

    /**
     * 注入抢购服务接口
     */
    @Resource(name = "panicBuyService")
    private MarketingService panicBuyService;

    /**
     * 注入直降服务接口
     */
    @Resource(name = "fallService")
    private MarketingService fallService;

    /**
     * 注入满减促销服务接口
     */
    @Resource(name = "fullDownService")
    private MarketingService fullDownService;

    /**
     * 注入满折促销服务接口
     */
    @Resource(name = "fullDiscountService")
    private MarketingService fullDiscountService;


    @Override
    public PageHelper<Marketing> queryMarketings(PageHelper<Marketing> pageHelper, String name, String type, long storeId) {
        logger.debug("queryMarketings and pageHelper:{} \r\n name:{} \r\n type:{} \r\n storeId:{}", pageHelper, name, type, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("type", type);
        params.put("storeId", storeId);
        return pageHelper.setListDates(marketingMapper.queryMarketings(pageHelper.getQueryParams(params, marketingMapper.queryMarketingCount(params))));
    }

    @Override
    public Marketing queryMarketingById(long id, long storeId) {

        logger.debug("queryMarketingById and id :{} , storeId:{}", id, storeId);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);


        // 促销基本信息
        Marketing marketing = marketingMapper.queryMarketingById(params);

        if (Objects.isNull(marketing)) {
            return marketing;
        }

        // 促销单品信息
        marketing.setMarketingSkus(marketingSkuService.queryMarketingSkusByMarketingId(id, storeId));

        // 设置促销的详细信息
        switch (marketing.getType()) {
            case MarketingConstant.FALL_MARKETING:
                // 直降
                fallService.setMarketingDetail(marketing);
                break;
            case MarketingConstant.GROUP_BUY_MARKETING:
                // 团购促销
                groupBuyService.setMarketingDetail(marketing);
                break;
            case MarketingConstant.PANIC_BUY_MARKETING:
                // 抢购促销
                panicBuyService.setMarketingDetail(marketing);
                break;
            case MarketingConstant.FULL_DOWN_MARKETING:
                // 满减
                fullDownService.setMarketingDetail(marketing);
                break;
            case MarketingConstant.FULL_DISCOUNT_MARKETING:
                // 满折
                fullDiscountService.setMarketingDetail(marketing);
                break;
            default:
                logger.error("no marketing detail....");
        }

        return marketing;
    }


    @Override
    public Marketing queryMarketingForShoppingCart(String skuId) {
        logger.debug("queryMarketingForShoppingCart and skuId:{}", skuId);
        return marketingMapper.queryMarketingForShoppingCart(skuId);
    }
}
