package com.lecshop.logistics.bean;

import com.lecshop.area.bean.City;
import lombok.Data;

/**
 * Created by dujinkai on 17/5/31.
 * 运费方式区域
 */
@Data
public class ShippingMethodArea {

    /**
     * 主键id
     */
    private long id;

    /**
     * 运费方式id
     */
    private long shippingMethodId;

    /**
     * 运费模版id
     */
    private long templateId;

    /**
     * 市id
     */
    private long cityId;

    /**
     * 市名称
     */
    private City city;
}
