package com.lecshop.spu.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/5/13.
 * 商品服务支持
 */
@Data
public class SpuServiceSupport {
    /**
     * 主键id
     */
    private long id;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 服务支持id
     */
    private long serviceSupportId;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;
}
