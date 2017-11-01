package com.lecshop.sku.service.impl;

import com.lecshop.sku.bean.SkuSpecValue;
import com.lecshop.sku.mapper.SkuSpecValueMapper;
import com.lecshop.sku.service.SkuSpecValueService;
import com.lecshop.spec.service.SpecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/13.
 * 单品规格值服务接口
 */
@Service
public class SkuSpecValueServiceImpl implements SkuSpecValueService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SkuSpecValueServiceImpl.class);

    /**
     * 单品规格值数据库接口
     */
    @Autowired
    private SkuSpecValueMapper skuSpecValueMapper;

    /**
     * 注入规格服务接口
     */
    @Autowired
    private SpecService specService;

    @Override
    public void addSkuSpecValues(List<SkuSpecValue> skuSpecValues) {

        logger.debug("addSkuSpecValues and skuSpecValues:{}", skuSpecValues);

        if (CollectionUtils.isEmpty(skuSpecValues)) {
            logger.warn("addSkuSpecValues not need...");
            return;
        }

        skuSpecValueMapper.addSkuSpecValues(skuSpecValues);
    }

    @Override
    public List<SkuSpecValue> queryBySkuId(String skuId) {
        logger.debug("queryBySkuId and skuId:{}", skuId);
        return skuSpecValueMapper.queryBySkuId(skuId).stream().map(skuSpecValue -> {
            skuSpecValue.setSpec(specService.querySpecByIdSimple(skuSpecValue.getSpecId()));
            return skuSpecValue;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteBySpuId(long spuId) {
        logger.debug("deleteBySpuId andspuId:{}", spuId);
        skuSpecValueMapper.deleteBySpuId(spuId);
    }

    @Override
    public void deleteBySpuIdPhysical(long spuId) {
        logger.debug("deleteBySpuIdPhysical and spuId:{}", spuId);

        skuSpecValueMapper.deleteBySpuIdPhysical(spuId);
    }
}
