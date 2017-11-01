package com.lecshop.type.service.impl;

import com.lecshop.category.bean.Category;
import com.lecshop.type.bean.Attribute;
import com.lecshop.type.bean.Type;
import com.lecshop.type.bean.TypeSpec;
import com.lecshop.type.mapper.TypeMapper;
import com.lecshop.type.service.TypeService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dujinkai on 17/5/10.
 * 类型服务接口实现类
 */
@Service
public class TypeServiceImpl implements TypeService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(TypeServiceImpl.class);

    /**
     * 注入类型数据库接口
     */
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageHelper<Type> queryTypes(PageHelper<Type> pageHelper, String name) {
        logger.debug("queryTypes and pageHelper : {} \r\n name : {} ", pageHelper, name);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(typeMapper.queryTypes(pageHelper.getQueryParams(params, typeMapper.queryTypesCount(params))));
    }

    @Transactional
    @Override
    public int addType(Type type) {

        logger.debug("addType and : {} ", type);

        // 新增类型主表
        typeMapper.addType(type);

        // 设置属性的类型id
        type.setAttributesTypeId();

        // 新增属性
        addAttributes(type.getAttributes());

        // 设置类型与规格关联的类型id
        type.setTypeSpecTypeId();

        // 新增类型和规格的关联关系
        addTypeAndSpec(type.getTypeSpecs());

        return 1;
    }

    @Transactional
    @Override
    public int deleteType(Type type) {
        logger.debug("deleteType and type : {}", type);

        if (Objects.isNull(type)) {
            logger.error("deleteType fail due to type is null...");
            return 0;
        }

        // 删除类型表
        typeMapper.deleteType(type);

        // 删除属性表
        typeMapper.deleteAttributes(type.getId());

        // 删除属性值表
        typeMapper.deleteAttributeValues(type.getId());

        // 删除类型和规格的关联关系表
        typeMapper.deleteTypeSpec(type.getId());

        return 1;
    }

    @Transactional
    @Override
    public int batchDeleteTypes(List<Type> types) {

        logger.debug("batchDeleteTypes and types : {}", types);

        if (CollectionUtils.isEmpty(types)) {
            logger.error("batchDeleteTypes fail due to types is empty.... ");
            return 0;
        }

        types.stream().forEach(this::deleteType);

        return 1;
    }

    @Override
    public Type queryTypeDetail(long typeId) {
        logger.debug("queryTypeDetail and typeId :{}", typeId);

        // 查询类型信息
        Type type = typeMapper.queryTypeById(typeId);

        if (Objects.isNull(type)) {
            logger.error("queryTypeDetail fail due to type is not exist....");
            return type;
        }

        // 查询类型关联的属性
        type.setAttributes(getAttributes(typeId));

        // 查询类型关联的规格
        type.setTypeSpecs(typeMapper.queryTypeSpecByTypeId(typeId));

        return type;
    }

    @Override
    public List<Type> queryAllType() {
        logger.debug("queryAllType ......");
        return typeMapper.queryAllType();
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        logger.debug("updateType and type:{}", type);

        if (Objects.isNull(type)) {
            logger.error("updateType fail due to type is null....");
            return 0;
        }

        // 更新类型信息
        typeMapper.updateType(type);


        // 更新属性
        updateAttributes(type);


        // 更新类型关联的规格
        updateTypeSpecs(type);

        return 1;
    }

    /**
     * 更新属性
     *
     * @param type 类型
     */
    private void updateAttributes(Type type) {

        logger.debug("updateAttributes and attributes:{}", type.getAttributes());

        // 根据类型id删除属性
        typeMapper.deleteAttributesByTypeIdPhysical(type.getId());

        // 根据类型id删除属性值
        typeMapper.deleteAttributeValuesByTypeIdPhysical(type.getId());

        if (CollectionUtils.isEmpty(type.getAttributes())) {
            logger.warn("do not need to updateAttributes");
            return;
        }

        // 设置属性的id
        setAttributesId(type.getAttributes());

        // 新增属性
        addAttributes(type.getAttributes());
    }

    /**
     * 设置属性的id
     */
    private void setAttributesId(List<Attribute> attributes) {
        IntStream.range(0, attributes.size()).forEach(index -> attributes.get(index).setCustomId(index));
    }

    /**
     * 更新类型的规格
     *
     * @param type 类型
     */
    private void updateTypeSpecs(Type type) {

        logger.debug("updateTypeSpecs and typeSpecs :{}", type.getTypeSpecs());

        typeMapper.deleteTypeSpecsByTypeIdPhysical(type.getId());

        if (CollectionUtils.isEmpty(type.getTypeSpecs())) {
            logger.warn("do not need to updateTypeSpecs....");
            return;
        }

        addTypeAndSpec(type.getTypeSpecs());
    }


    /**
     * 查询类型的属性
     *
     * @param typeId 类型id
     * @return 返回类型的属性
     */
    private List<Attribute> getAttributes(long typeId) {
        return typeMapper.queryAttributesByTypeId(typeId).stream().map(this::setAttributeValues).collect(Collectors.toList());
    }

    /**
     * 设置属性的属性值
     *
     * @param attribute 属性
     */
    private Attribute setAttributeValues(Attribute attribute) {
        attribute.setAttributeValues(typeMapper.queryAttributeValueByAttributeId(attribute.getId()));
        return attribute;
    }


    /**
     * 新增属性
     *
     * @param attributes 属性集合
     */
    private void addAttributes(List<Attribute> attributes) {
        logger.debug("addAttributes and attributes : {} ", attributes);

        if (CollectionUtils.isEmpty(attributes)) {
            logger.error("addAttributes fail due to params is empty....");
            return;
        }

        // 新增属性
        typeMapper.addAttributes(attributes);

        // 添加属性值
        attributes.stream().forEach(this::addAttributeValues);
    }

    /**
     * 新增属性值
     *
     * @param attribute 属性
     */
    private void addAttributeValues(Attribute attribute) {
        logger.debug("addAttributeValues and attributeValues:{}", attribute.getAttributeValues());

        if (CollectionUtils.isEmpty(attribute.getAttributeValues())) {
            logger.error("addAttributeValues fail due to params is empty.....");
            return;
        }

        // 设置属性值的id
        attribute.setAttributeValuesId();

        // 新增属性值
        typeMapper.addAttributeValues(attribute.getAttributeValues());
    }

    /**
     * 新增类型和规格的关联关系
     *
     * @param typeSpecs 类型规格
     */
    private void addTypeAndSpec(List<TypeSpec> typeSpecs) {
        logger.debug("addTypeAndSpu and typeSpecs : {}", typeSpecs);

        if (CollectionUtils.isEmpty(typeSpecs)) {
            logger.error("addTypeAndSpu fail due to typeSpecs is empty....");
            return;
        }
        typeMapper.addTypeAndSpec(typeSpecs);
    }
}
