package com.lecshop.logistics.mapper;

import com.lecshop.logistics.bean.ShippingMethodArea;

import java.util.List;

/**
 * Created by dujinkai on 17/5/31.
 * 运费方式区域数据库接口
 */
public interface ShippingMethodAreaMapper {

    /**
     * 新增运费方式区域
     *
     * @param shippingMethodAreas 运费方式区域
     */
    void addShippingMethodAreas(List<ShippingMethodArea> shippingMethodAreas);

    /**
     * 根据运费方式id查询运费方式的区域
     *
     * @param id 运费方式id
     * @return 返回运费方式区域
     */
    List<ShippingMethodArea> queryByShippingMethodId(long id);

    /**
     * 根据运费模版id删除运费方式区域
     *
     * @param id 运费模版id
     */
    void deleteByTemplateId(long id);
}
