package com.lecshop.spu.service.impl;

import com.lecshop.spu.bean.SpuImage;
import com.lecshop.spu.mapper.SpuImageMapper;
import com.lecshop.spu.service.SpuImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by dujinkai on 17/5/17.
 * 商品图片服务接口实现
 */
@Service
public class SpuImageServiceImpl implements SpuImageService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpuImageServiceImpl.class);

    /**
     * 注入商品图片
     */
    @Autowired
    private SpuImageMapper spuImageMapper;

    @Override
    public void addSpuImages(List<SpuImage> spuImages) {
        logger.debug("addSpuImages and spuImages:{}", spuImages);

        if (CollectionUtils.isEmpty(spuImages)) {
            logger.warn("addSpuImages not need....");
            return;
        }

        spuImageMapper.addSpuImages(spuImages);
    }

    @Override
    public void deleteBySpuId(long spuId) {
        logger.debug("deleteBySpuId and spuId:{}", spuId);
        spuImageMapper.deleteBySpuId(spuId);
    }

    @Override
    public List<SpuImage> queryBySpuId(long spuId) {
        logger.debug("queryBySpuId and spuId:{}", spuId);
        return spuImageMapper.queryBySpuId(spuId);
    }

    @Override
    public void updateSpuImages(List<SpuImage> spuImages, long spuId) {
        logger.debug("updateSpuImages and spuImages:{} \r\n spuId:{}", spuImages, spuId);

        // 删除图片和商品的关联关系(物理)
        spuImageMapper.deleteBySpuIdPhysical(spuId);

        if (CollectionUtils.isEmpty(spuImages)) {
            logger.warn("not need to updateSpuImages");
            return;
        }

        // 新增
        addSpuImages(spuImages);
    }
}
