package com.lecshop.type.mapper;

import com.lecshop.type.bean.Attribute;
import com.lecshop.type.bean.AttributeValue;
import com.lecshop.type.bean.Type;
import com.lecshop.type.bean.TypeSpec;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/10.
 * 类型数据库接口
 */
public interface TypeMapper {

    /**
     * 查询类型总数
     *
     * @param params 查询 参数
     * @return 返回类型总数
     */
    int queryTypesCount(Map<String, Object> params);

    /**
     * 分页查询类型信息
     *
     * @param params 查询参数
     * @return 返回类型信息
     */
    List<Type> queryTypes(Map<String, Object> params);

    /**
     * 新增商品类型
     *
     * @param type 商品类型
     * @return 成功返回1  失败返回0
     */
    int addType(Type type);

    /**
     * 新增属性
     *
     * @param attributes 属性集合
     */
    void addAttributes(List<Attribute> attributes);

    /**
     * 新增属性值
     *
     * @param attributeValues 属性值集合
     */
    void addAttributeValues(List<AttributeValue> attributeValues);

    /**
     * 新增类型和规格的关联关系
     *
     * @param typeSpecs 类型规格集合
     */
    void addTypeAndSpec(List<TypeSpec> typeSpecs);

    /**
     * 删除类型
     *
     * @param type 类型实体
     * @return 成功返回1  失败返回0
     */
    int deleteType(Type type);

    /**
     * 根据类型id删除属性
     *
     * @param typeId 类型id
     */
    void deleteAttributes(long typeId);

    /**
     * 根据类型id删除属性值
     *
     * @param typeId 类型id
     */
    void deleteAttributeValues(long typeId);

    /**
     * 根据类型id 删除类型和规格的关联关系
     *
     * @param typeId
     */
    void deleteTypeSpec(long typeId);

    /**
     * 根据id查询类型
     *
     * @param id 类型id
     * @return 返回类型信息
     */
    Type queryTypeById(long id);


    /**
     * 根据类型id查询属性信息
     *
     * @param typeId 类型id
     * @return 返回类型的属性
     */
    List<Attribute> queryAttributesByTypeId(long typeId);

    /**
     * 根据类型id查询类型关联的规格信息
     *
     * @param typeId 类型id
     * @return 返回类型关联的规格
     */
    List<TypeSpec> queryTypeSpecByTypeId(long typeId);

    /**
     * 根据属性id查询属性值
     *
     * @param id 属性id
     * @return 返回属性的属性值
     */
    List<AttributeValue> queryAttributeValueByAttributeId(String id);

    /**
     * 查询所有的类型信息
     *
     * @return 返回所有的类型信息
     */
    List<Type> queryAllType();

    /**
     * 更新类型
     *
     * @param type 类型信息
     * @return 成功返回1  失败返回0
     */
    int updateType(Type type);

    /**
     * 根据类型id删除属性(物理删除)
     *
     * @param id 类型id
     */
    void deleteAttributesByTypeIdPhysical(long typeId);

    /**
     * 根据类型id 删除属性值(物理删除)
     *
     * @param typeId 类型id
     */
    void deleteAttributeValuesByTypeIdPhysical(long typeId);

    /**
     * 根据类型id 删除类型规格 (物理删除)
     *
     * @param typeId 类型id
     */
    void deleteTypeSpecsByTypeIdPhysical(long typeId);
}
