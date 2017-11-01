package com.lecshop.elasticsearch.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Created by dujinkai on 17/6/20.
 * ES中的属性值
 */
@Data
public class EsAttributeValue implements Serializable {

    /**
     * 属性id
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String attributeId;

    /**
     * 属性名称
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String attributeName;

    /**
     * 属性值id
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String attributeValueId;

    /**
     * 属性值
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String attributeValue;

    /**
     * 构造空实体
     *
     * @return 返回空实体
     */
    public static EsAttributeValue buildEmpty() {
        return new EsAttributeValue();
    }

}
