package com.lecshop.marketing.service.impl;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.mapper.FreeShipMapper;
import com.lecshop.marketing.mapper.MarketingMapper;
import com.lecshop.marketing.service.FreeShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Created by dujinkai on 17/6/9.
 * 包邮服务接口
 */
@Service
public class FreeShipServiceImpl implements FreeShipService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(FreeShipServiceImpl.class);

    /**
     * 注入包邮数据库接口
     */
    @Autowired
    private FreeShipMapper freeShipMapper;

    /**
     * 注入促销数据库接口
     */
    @Autowired
    private MarketingMapper marketingMapper;

    @Override
    public int addMarketing(Marketing marketing) {

        // 包邮促销已经存在 则先删除原来的包邮促销
        if (isFreeShippingExist(marketing.getStoreId())) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", marketing.getId());
            params.put("storeId", marketing.getStoreId());
            marketingMapper.deleteMarketing(params);
            freeShipMapper.deleteFreeShip(marketing.getId());
        }

        // 新增包邮
        marketingMapper.addMarketing(marketing);

        marketing.setLinkedMarketingId();

        return freeShipMapper.addFreeShip(marketing.getFreeShip());
    }

    @Override
    public Marketing queryFreeShip(long storeId) {
        logger.debug("queryFreeShip ....storeId:{}", storeId);
        Marketing marketing = freeShipMapper.queryFreeShip(storeId);

        return Objects.nonNull(marketing) ? marketing : new Marketing();
    }

    /**
     * 判断包邮促销是否存在
     *
     * @param storeId 店铺id
     * @return 存在返回true  不存在返回fasle
     */
    private boolean isFreeShippingExist(long storeId) {
        return Objects.nonNull(queryFreeShip(storeId));
    }
}
