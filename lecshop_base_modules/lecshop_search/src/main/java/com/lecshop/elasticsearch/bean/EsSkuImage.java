package com.lecshop.elasticsearch.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Created by dujinkai on 17/5/14.
 * 单品图片
 */
@Data
public class EsSkuImage implements Serializable {

    /**
     * 图片地址
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String url;


    /**
     * 构造空实体
     *
     * @return 返回空实体
     */
    public static EsSkuImage buildEmpty() {
        return new EsSkuImage();
    }
}
