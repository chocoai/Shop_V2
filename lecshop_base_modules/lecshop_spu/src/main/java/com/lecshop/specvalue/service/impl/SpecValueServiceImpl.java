package com.lecshop.specvalue.service.impl;

import com.lecshop.spec.bean.Spec;
import com.lecshop.specvalue.bean.SpecValue;
import com.lecshop.specvalue.mapper.SpecValueMapper;
import com.lecshop.specvalue.service.SpecValueService;
import com.lecshop.spu.service.SpuSpecValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Created by dujinkai on 17/5/8.
 * 规格值服务接口实现类
 */
@Service
public class SpecValueServiceImpl implements SpecValueService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpecValueServiceImpl.class);

    /**
     * 注入规格值数据库接口
     */
    @Autowired
    private SpecValueMapper specValueMapper;

    /**
     * 注入商品规格值服务接口
     */
    @Autowired
    private SpuSpecValueService spuSpecValueService;

    @Override
    public String addSpecValue(SpecValue specValue) {
        logger.debug("addSpecValue and specValue:{}", specValue);

        if (Objects.isNull(specValue)) {
            logger.error("addSpecValue fail due to specValue is null...");
            return "";
        }
        specValueMapper.addSpecValue(specValue);
        return specValue.getId();
    }

    @Override
    public void addSpecValues(List<SpecValue> specValues) {
        logger.debug("addSpecValues and specValues {}", specValues);

        if (CollectionUtils.isEmpty(specValues)) {
            logger.error("addSpecValues fail due to params is empty....");
            return;
        }

        // 设置规格值的主键id
        setSpecValueId(specValues);

        specValueMapper.addSpecValues(specValues);
    }


    @Override
    public List<SpecValue> querySpecValuesBySpecId(long specId) {
        logger.debug("querySpecValuesBySpecId and specId:{}", specId);
        return specValueMapper.querySpecValuesBySpecId(specId);
    }

    @Override
    public void deleteBySpecId(SpecValue specValue) {
        logger.debug("deleteBySpecId and specValue : {}", specValue);
        specValueMapper.deleteBySpecId(specValue);
    }

    @Override
    public void updateSpecValues(Spec spec) {

        logger.debug("updateSpecValues and spec : {}", spec);

        // 第一步 根据规格id删除规格值信息
        specValueMapper.deleteBySpecIdPhysics(spec.getId());

        if (CollectionUtils.isEmpty(spec.getSpecValues())) {
            logger.error("updateSpecValues fail due to specValues is empty....");
            return;
        }

        // 第二步 新增规格值信息
        setSpecValueId(spec.getSpecValues());

        specValueMapper.addSpecValues(spec.getSpecValues());
    }

    @Override
    public boolean isSpecValueCanDelete(String specValueId) {
        logger.debug("isSpecValueCanDelete and specValueId:{}", specValueId);

        return spuSpecValueService.queryCountBySpecValueId(specValueId) == 0;
    }

    /**
     * 设置规格值的主键id
     *
     * @param specValues 规格值
     */
    private void setSpecValueId(List<SpecValue> specValues) {
        IntStream.range(0, specValues.size()).forEach(index -> specValues.get(index).setCustomId(index));
    }

}
