package com.lecshop.spu.service.impl;

import com.lecshop.spec.service.SpecService;
import com.lecshop.specvalue.service.SpecValueService;
import com.lecshop.spu.bean.SpuSpecValue;
import com.lecshop.spu.mapper.SpuSpecValueMapper;
import com.lecshop.spu.service.SpuSpecValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/13.
 * 商品规格值服务实现接口
 */
@Service
public class SpuSpecValueServiceImpl implements SpuSpecValueService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpuSpecValueServiceImpl.class);

    /**
     * 注入商品规格值信息
     */
    @Autowired
    private SpuSpecValueMapper spuSpecValueMapper;

    /**
     * 注入规格值服务接口
     */
    @Autowired
    private SpecService specService;

    @Override
    public void addSpuSpecValues(List<SpuSpecValue> spuSpecValues) {

        logger.debug("addSpuSpeValues and spuSpecValues:{}", spuSpecValues);

        if (CollectionUtils.isEmpty(spuSpecValues)) {
            logger.warn("do not need to addSpuSpeValues");
            return;
        }
        spuSpecValueMapper.addSpuSpecValues(spuSpecValues);
    }

    @Override
    public List<SpuSpecValue> queryBySpuIdWithSpec(long spuId) {
        logger.debug("queryById and spuId:{}", spuId);
        return spuSpecValueMapper.queryBySpuId(spuId).parallelStream().map(spuSpecValue -> spuSpecValue.setSpec(specService.querySpecById(spuSpecValue.getSpecId()))).collect(Collectors.toList());
    }

    @Override
    public void deleteBySpuId(long spuId) {
        logger.debug("deleteBySpuId and spuId:{}", spuId);

        spuSpecValueMapper.deleteBySpuId(spuId);
    }

    @Override
    public void updateSpuSpecValues(List<SpuSpecValue> spuSpecValues, long spuId) {
        logger.debug("updateSpuSpecValues and spuSpecValues:{} \r\n spuId:{}", spuSpecValues, spuId);

        // 删除商品规格值(物理)
        spuSpecValueMapper.deleteBySpuIdPhysical(spuId);

        if (CollectionUtils.isEmpty(spuSpecValues)) {
            logger.warn("not need to updateSpuSpecValues");
            return;
        }

        // 新增商品规格值
        addSpuSpecValues(spuSpecValues);
    }

    @Override
    public int queryCountBySpecId(long specId) {
        logger.debug("queryCountBySpecId and specId:{}", specId);
        return spuSpecValueMapper.queryCountBySpecId(specId);
    }

    @Override
    public int queryCountBySpecValueId(String specValueId) {
        logger.debug("queryCountBySpecValueId and specValueId:{}", specValueId);
        return spuSpecValueMapper.queryCountBySpecValueId(specValueId);
    }
}
