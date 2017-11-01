package com.lecshop.sku.service.impl;

import com.lecshop.sku.bean.SkuImage;
import com.lecshop.sku.mapper.SkuImageMapper;
import com.lecshop.sku.service.SkuImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 单品图片服务实现类
 */
@Service
public class SkuImageServiceImpl implements SkuImageService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SkuImageServiceImpl.class);

    /**
     * 注入单品图片接口
     */
    @Autowired
    private SkuImageMapper skuImageMapper;

    @Override
    public void addSkuImages(List<SkuImage> skuImages) {
        logger.debug("addSkuImages and skuImages:{}", skuImages);

        if (CollectionUtils.isEmpty(skuImages)) {
            logger.warn("not need to addSkuImages due to skuImages is empty....");
            return;
        }

        skuImageMapper.addSkuImages(skuImages);
    }

    @Override
    public List<SkuImage> queryBySkuId(String skuId) {
        logger.debug("queryBySkuId and skuId:{}", skuId);
        return skuImageMapper.queryBySkuId(skuId);
    }

    @Override
    public void deleteBySpuId(long spuId) {
        logger.debug("deleteBySpuId and spuId:{}", spuId);
        skuImageMapper.deleteBySpuId(spuId);
    }

    @Override
    public void deleteBySpuIdPhysical(long spuId) {
        logger.debug("deleteBySpuIdPhysical and spuId:{}", spuId);

        skuImageMapper.deleteBySpuIdPhysical(spuId);
    }
}
