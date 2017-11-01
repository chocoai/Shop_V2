package com.lecshop.logistics.mapper;

import com.lecshop.logistics.bean.ShippingMethod;

import java.util.List;

/**
 * Created by dujinkai on 17/5/31.
 * 运费方式数据库接口
 */
public interface ShippingMethodMapper {

    /**
     * 新增运费方式
     *
     * @param shippingMethods 运费方式
     */
    void addShippingMethods(List<ShippingMethod> shippingMethods);

    /**
     * 根据物流模版id查询运费方式
     *
     * @param id 物流模版id
     * @return 返回物流模版的运费方式
     */
    List<ShippingMethod> queryByTemplateId(long id);

    /**
     * 删除运费方式
     *
     * @param id 运费方式id
     */
    void deleteByTemplateId(long id);
}
