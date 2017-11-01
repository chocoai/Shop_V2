package com.lecshop.spu.service.impl;

import com.lecshop.spu.bean.SpuServiceSupport;
import com.lecshop.spu.mapper.SpuServicceSupportServiceMapper;
import com.lecshop.spu.service.SpuServiceSupportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by dujinkai on 17/5/13.
 * 商品服务支持 接口实现类
 */
@Service
public class SpuServiceSupportServiceImpl implements SpuServiceSupportService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpuServiceSupportServiceImpl.class);

    /**
     * 商品服务支持
     */
    @Autowired
    private SpuServicceSupportServiceMapper spuServicceSupportServiceMapper;

    @Override
    public void addSpuServicceSupport(List<SpuServiceSupport> spuServiceSupports) {

        logger.debug("addSpuServicceSupport and spuServiceSupports:{}", spuServiceSupports);

        if (CollectionUtils.isEmpty(spuServiceSupports)) {
            logger.warn("do not need to addSpuServicceSupport");
            return;
        }

        spuServicceSupportServiceMapper.addSpuServicceSupportServices(spuServiceSupports);
    }

    @Override
    public List<SpuServiceSupport> queryBySpuId(long spuId) {
        logger.debug("queryBySpuId and spuId:{}", spuId);
        return spuServicceSupportServiceMapper.queryBySpuId(spuId);
    }

    @Override
    public void deleteBySpuId(long spuId) {
        logger.debug("deleteBySpuId and spuId:{}", spuId);

        spuServicceSupportServiceMapper.deleteBySpuId(spuId);
    }

    @Override
    public void updateSpuServiceSupport(List<SpuServiceSupport> spuServiceSupports, long spuId) {
        logger.debug("updateSpuServiceSupport and spuServiceSupports:{} \r\n spuId:{}", spuServiceSupports, spuId);

        // 删除商品服务支持(物理)
        spuServicceSupportServiceMapper.deleteBySpuIdPhysical(spuId);

        if (CollectionUtils.isEmpty(spuServiceSupports)) {
            logger.warn("not need to updateSpuServiceSupport");
            return;
        }

        // 新增商品服务支持
        addSpuServicceSupport(spuServiceSupports);
    }
}
