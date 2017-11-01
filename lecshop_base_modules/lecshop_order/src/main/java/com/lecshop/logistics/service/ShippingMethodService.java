package com.lecshop.logistics.service;

import com.lecshop.logistics.bean.ShippingMethod;

import java.util.List;

/**
 * Created by dujinkai on 17/5/31.
 * 运费方式服务接口
 */
public interface ShippingMethodService {

    /**
     * 新增运费方式
     *
     * @param shippingMethods 运费方式
     */
    void addShippingMethod(List<ShippingMethod> shippingMethods);

    /**
     * 根据物流模版id查询运费方式
     *
     * @param id 物流模版id
     * @return 返回物流模版的运费方式
     */
    List<ShippingMethod> queryByTemplateId(long id);

    /**
     * 根据物流模版id删除运费方式
     *
     * @param id 物流模版id
     */
    void deleteByTemplateId(long id);

    /**
     * 更新运费方式
     *
     * @param shippingMethods 运费方式
     * @param templateId      物流模版id
     */
    void updateShippingMethods(List<ShippingMethod> shippingMethods, long templateId);
}
