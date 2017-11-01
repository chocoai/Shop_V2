package com.lecshop.exceptionpage.bean;

import lombok.Data;

/**
 * 异常页面实体类
 *
 * @author sunluyang on 2017/5/19.
 */
@Data
public class ExceptionPage {
    /**
     * 主键id
     */
    private long id;
    /**
     * 页面标题
     */
    private String title;
    /**
     * 错误类型  1:404 错误 2:500错误  3:400错误
     */
    private String type;
    /**
     * 是否使用  0 使用 1 不使用 默认使用
     */
    private String isUse;
    /**
     * 页面描述
     */
    private String desc;
}
