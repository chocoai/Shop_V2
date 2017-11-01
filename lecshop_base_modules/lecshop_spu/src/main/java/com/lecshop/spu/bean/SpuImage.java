package com.lecshop.spu.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/5/17.
 * 商品图片
 */
@Data
public class SpuImage {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品图片
     */
    private long spuId;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 删除标记 0 未删除 1删除
     */
    private String delFlag;
}
