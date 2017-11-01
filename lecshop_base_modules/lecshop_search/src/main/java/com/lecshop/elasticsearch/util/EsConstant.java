package com.lecshop.elasticsearch.util;

/**
 * Created by dujinkai on 17/5/14.
 * es常量接口
 */
public interface EsConstant {

    /**
     * 索引名称
     */
    String INDEX_NAME = "es-sku-index";

    /**
     * 文档类型
     */
    String DOCUMENT_TYPE = "EsSku";

    /**
     * 不过滤
     */
    int NO_FILTER = -1;

    /**
     * 不排序
     */
    int NO_SORT = -1;
}
