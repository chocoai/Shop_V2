package com.lecshop.spuimport.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/5/18.
 * 商品导入
 */
@Data
public class SpuImport {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * seo标题
     */
    private String seoTitle;

    /**
     * seo关键字
     */
    private String seoKeywords;

    /**
     * seo描述
     */
    private String seoDesc;

    /**
     * 是否发布 0未发布 1 发布
     */
    private String isRelease;

    /**
     * 删除标记  0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 构造空实体
     *
     * @return 返回空实体
     */
    public static SpuImport buildEmpty() {
        return new SpuImport();
    }

    /**
     * 设置新增的默认值
     */
    public void setDefalutValuesFoAdd() {
        this.delFlag = "0";
        this.isRelease = "0";
    }
}
