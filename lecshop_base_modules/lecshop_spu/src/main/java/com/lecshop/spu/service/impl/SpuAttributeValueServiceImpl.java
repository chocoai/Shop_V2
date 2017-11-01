package com.lecshop.spu.service.impl;

import com.lecshop.spu.bean.SpuAttributeValue;
import com.lecshop.spu.mapper.SpuAttributeValueMapper;
import com.lecshop.spu.service.SpuAttributeValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by dujinkai on 17/5/17.
 * 商品属性值服务接口实现
 */
@Service
public class SpuAttributeValueServiceImpl implements SpuAttributeValueService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpuAttributeValueServiceImpl.class);

    /**
     * 注入商品属性值数据库接口
     */
    @Autowired
    private SpuAttributeValueMapper spuAttributeValueMapper;

    @Override
    public void addSpuAttributeValues(List<SpuAttributeValue> spuAttributeValues) {
        logger.debug("addSpuAttributeValues and spuAttributeValues:{}", spuAttributeValues);

        if (CollectionUtils.isEmpty(spuAttributeValues)) {
            logger.warn("addSpuAttributeValues not need...");
            return;
        }
        spuAttributeValueMapper.addSpuAttributeValues(spuAttributeValues);
    }

    @Override
    public void deleteBySpuId(long spuId) {
        logger.debug("deleteBySpuId and spuId:{}", spuId);

        spuAttributeValueMapper.deleteBySpuId(spuId);
    }

    @Override
    public List<SpuAttributeValue> queryBySpuId(long spuId) {
        logger.debug("queryBySpuId and spuId:{}", spuId);
        return spuAttributeValueMapper.queryBySpuId(spuId);
    }

    @Override
    public void updateSpuAttributValues(List<SpuAttributeValue> spuAttributeValues, long spuId) {
        logger.debug("updateSpuAttributValues and spuAttributeValues:{} \r\n spuId :{}", spuAttributeValues, spuId);

        // 首先删除商品属性值(物理)
        spuAttributeValueMapper.deleteBySpuIdPhysical(spuId);

        if (CollectionUtils.isEmpty(spuAttributeValues)) {
            logger.warn("not need to updateSpuAttributValues");
            return;
        }

        // 新增商品属性值
        addSpuAttributeValues(spuAttributeValues);
    }
}
