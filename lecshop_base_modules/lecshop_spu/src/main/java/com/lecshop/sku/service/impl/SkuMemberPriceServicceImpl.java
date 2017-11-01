package com.lecshop.sku.service.impl;

import com.lecshop.sku.bean.SkuMemberPrice;
import com.lecshop.sku.mapper.SkuMemberPriceMapper;
import com.lecshop.sku.service.SkuMemberPriceServicce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 单品会员价服务接口实现类
 */
@Service
public class SkuMemberPriceServicceImpl implements SkuMemberPriceServicce {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SkuMemberPriceServicceImpl.class);

    /**
     * 注入单品会员价数据库接口
     */
    @Autowired
    private SkuMemberPriceMapper skuMemberPriceMapper;

    @Override
    public void addSkuMemberPrices(List<SkuMemberPrice> skuMemberPrices) {

        logger.debug("addSkuMemberPrices and skuMemberPrices:{}", skuMemberPrices);

        if (CollectionUtils.isEmpty(skuMemberPrices)) {
            logger.warn("do not need to addSkuMemberPrices");
            return;
        }

        skuMemberPriceMapper.addSkuMemberPrices(skuMemberPrices);
    }

    @Override
    public List<SkuMemberPrice> queryBySkuId(String skuId) {
        logger.debug("queryBySkuId and skuId:{}", skuId);
        return skuMemberPriceMapper.queryBySkuId(skuId);
    }

    @Override
    public void deleteBySpuId(long spuId) {
        logger.debug("deleteBySpuId and spuId:{}", spuId);

        skuMemberPriceMapper.deleteBySpuId(spuId);
    }

    @Override
    public void deleteBySpuIdPhysical(long spuId) {
        logger.debug("deleteBySpuIdPhysical and spuId:{}", spuId);

        skuMemberPriceMapper.deleteBySpuIdPhysical(spuId);
    }

    @Override
    public void deleteByLevelId(long customerLevelId) {
        logger.debug("deleteByLevelId and customerLevelId:{}", customerLevelId);

        skuMemberPriceMapper.deleteByLevelId(customerLevelId);
    }
}
