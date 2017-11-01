package com.lecshop.type.bean;

import lombok.Data;


/**
 * Created by dujinkai on 17/5/11.
 * 类型和规格的关联关系
 */
@Data
public class TypeSpec {

    /**
     * 主键id
     */
    private long id;

    /**
     * 类型id
     */
    private long typeId;

    /**
     * 规格id
     */
    private long specId;


    /**
     * 删除标记 0未删除 1 删除 默认0
     */
    private String delFlag = "0";

}
