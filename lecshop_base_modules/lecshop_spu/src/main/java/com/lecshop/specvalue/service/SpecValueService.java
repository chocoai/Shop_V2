package com.lecshop.specvalue.service;

import com.lecshop.spec.bean.Spec;
import com.lecshop.specvalue.bean.SpecValue;

import java.util.List;

/**
 * Created by dujinkai on 17/5/8.
 * 规格值服务接口
 */
public interface SpecValueService {

    /**
     * 添加规格值
     *
     * @param specValue 规格值信息
     * @return 成功返回1 失败返回0
     */
    String addSpecValue(SpecValue specValue);

    /**
     * 新增规格值
     *
     * @param specValues 规格值集合
     */
    void addSpecValues(List<SpecValue> specValues);

    /**
     * 根据规格id 查询规格值信息
     *
     * @param specId 规格id
     * @return 返回规格值信息集合
     */
    List<SpecValue> querySpecValuesBySpecId(long specId);

    /**
     * 删除规格值
     *
     * @param specValue 规格信息
     */
    void deleteBySpecId(SpecValue specValue);

    /**
     * 修改规格值
     *
     * @param spec 规格信息
     */
    void updateSpecValues(Spec spec);

    /**
     * 判断规格值是否可以删除
     *
     * @param specValueId 规格值id
     * @return 可以返回true  不可以返回false
     */
    boolean isSpecValueCanDelete(String specValueId);
}
