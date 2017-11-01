package com.lecshop.specvalue.mapper;

import com.lecshop.specvalue.bean.SpecValue;

import java.util.List;

/**
 * Created by dujinkai on 17/5/8.
 * 规格值数据库接口
 */
public interface SpecValueMapper {

    /**
     * 批量新增规格值
     *
     * @param specValues 规格值集合
     */
    void addSpecValues(List<SpecValue> specValues);

    /**
     * 根据规格id查询规格值信息
     *
     * @param specId 规格id
     * @return 返回规格值信息集合
     */
    List<SpecValue> querySpecValuesBySpecId(long specId);

    /**
     * 根据规格id 删除规格值
     *
     * @param specValue 规格信息
     */
    void deleteBySpecId(SpecValue specValue);

    /**
     * 根据规格id 删除规格值 (特别注意 该删除是直接物理删除)
     *
     * @param specId 规格id
     */
    void deleteBySpecIdPhysics(long specId);

    /**
     * 添加规格值
     *
     * @param specValue 规格值信息
     * @return 成功返回 1 失败返回0
     */
    int addSpecValue(SpecValue specValue);
}
