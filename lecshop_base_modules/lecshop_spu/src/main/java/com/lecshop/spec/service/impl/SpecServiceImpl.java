package com.lecshop.spec.service.impl;

import com.lecshop.spec.bean.Spec;
import com.lecshop.spec.mapper.SpecMapper;
import com.lecshop.spec.service.SpecService;
import com.lecshop.specvalue.bean.SpecValue;
import com.lecshop.specvalue.service.SpecValueService;
import com.lecshop.spu.service.SpuSpecValueService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/8.
 * 规格服务接口实现类
 */
@Service
public class SpecServiceImpl implements SpecService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpecServiceImpl.class);

    /**
     * 注入规格数据库接口
     */
    @Autowired
    private SpecMapper specMapper;

    /**
     * 注入规格值服务接口
     */
    @Autowired
    private SpecValueService specValueService;

    /**
     * 注入商品规格值服务
     */
    @Autowired
    private SpuSpecValueService spuSpecValueService;

    @Transactional
    @Override
    public int addSpec(Spec spec) {
        logger.debug("addSpec and  spec {}", spec);

        // 首先增加规格主表
        specMapper.addSpec(spec);

        logger.debug("addSpec success and begin to add specvalue....");

        // 新增规格值表
        addSpecValues(spec);

        return 1;
    }

    @Override
    public PageHelper<Spec> querySpecs(PageHelper<Spec> pageHelper, String name) {
        logger.debug("querySpecs and pageHelper :{} \r\n name : {}", pageHelper, name);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(specMapper.querySpecs(pageHelper.getQueryParams(params, specMapper.querySpecsCount(params))));
    }

    @Override
    public Spec querySpecById(long id) {
        logger.debug("querySpecById and id : {}", id);

        Spec spec = specMapper.querySpecById(id);

        spec.setSpecValues(specValueService.querySpecValuesBySpecId(spec.getId()));

        return spec;
    }

    @Override
    public Spec querySpecByIdSimple(long id) {
        logger.debug("querySpecByIdSimple and id:{}", id);
        return specMapper.querySpecById(id);
    }

    @Override
    public int deleteSpec(Spec spec) {
        logger.debug("deleteSpec and spec  : {}", spec);

        // 判断规格是否可以删除 (如果被商品关联了则不能删除)
        if (!isCanDelete(spec)) {
            logger.error("deleteSpec fail due to spec:{} has spus...", spec);
            return -1;
        }

        // 删除规格
        specMapper.deleteSpec(spec);

        // 删除规格值
        specValueService.deleteBySpecId(SpecValue.buildForDelete(spec.getId(), spec.getDelName()));

        return 1;
    }


    @Override
    public int deleteSpecs(List<Spec> specs) {
        logger.debug("deleteSpecs and specs : {}", specs);

        if (CollectionUtils.isEmpty(specs)) {
            logger.error("deleteSpecs fail due to specs is empty....");
            return 0;
        }

        // 判断规格是否可以删除
        if (specs.stream().map(this::isCanDelete).filter(Boolean::booleanValue).count() != specs.size()) {
            logger.error("deleteSpecs fail due to specs has spu...");
            return -1;
        }


        specs.stream().forEach(this::deleteSpec);

        return 1;
    }

    @Transactional
    @Override
    public int updateSpec(Spec spec) {
        logger.debug("updateSpec and spec : {}", spec);

        // 修改规格信息
        specMapper.updateSpec(spec);

        // 修改规格值信息
        specValueService.updateSpecValues(spec);

        return 1;
    }


    @Override
    public List<Spec> queryAllSpec() {
        logger.debug("queryAllSpec ....");
        return specMapper.queryAllSpec();
    }

    @Override
    public List<Spec> querySpecsByIds(Long[] ids) {
        logger.debug("querySpecsByIds and ids :{}", ids);
        if (ArrayUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return Arrays.stream(ids).map(this::querySpecById).collect(Collectors.toList());
    }

    /**
     * 新增规格值
     *
     * @param spec 规格
     */
    private void addSpecValues(Spec spec) {

        if (CollectionUtils.isEmpty(spec.getSpecValues())) {
            logger.warn("do not need to addSpecValues due to values is empty....");
            return;
        }

        setSpecId(spec.getId(), spec.getSpecValues());

        // 新增规格值信息
        specValueService.addSpecValues(spec.getSpecValues());

    }

    /**
     * 给规格值设置规格id
     *
     * @param specId 规格id
     */
    private void setSpecId(long specId, List<SpecValue> specValues) {
        specValues.stream().forEach(specValue -> specValue.setSpecId(specId));
    }


    /**
     * 判断规格是否可以删除
     *
     * @param spec 规格信息
     * @return 可以返回true  不可以返回false
     */
    private boolean isCanDelete(Spec spec) {
        return spuSpecValueService.queryCountBySpecId(spec.getId()) == 0;
    }


}
