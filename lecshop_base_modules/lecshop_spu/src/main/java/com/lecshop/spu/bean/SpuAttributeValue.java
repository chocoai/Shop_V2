package com.lecshop.spu.bean;

import com.lecshop.elasticsearch.bean.EsAttributeValue;
import lombok.Data;

/**
 * Created by dujinkai on 17/5/17.
 * 商品下面的属性值
 */
@Data
public class SpuAttributeValue {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 属性id
     */
    private String attributeId;

    /**
     * 属性名称
     */
    private String attributeName;

    /**
     * 属性值id
     */
    private String attributeValueId;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * 删除标记 0 未删除 1 删
     */
    private String delFlag;

    /**
     * 获得es的属性值
     *
     * @return 返回es的属性值
     */
    public EsAttributeValue convertEsAttributeValue() {
        EsAttributeValue esAttributeValue = EsAttributeValue.buildEmpty();
        esAttributeValue.setAttributeId(this.attributeId);
        esAttributeValue.setAttributeName(this.attributeName);
        esAttributeValue.setAttributeValue(this.attributeValue);
        esAttributeValue.setAttributeValueId(this.attributeValueId);
        return esAttributeValue;
    }
}
