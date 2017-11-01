package com.lecshop.spu.bean;

import com.lecshop.spec.bean.Spec;
import lombok.Data;

/**
 * Created by dujinkai on 17/5/13.
 * 商品的规格值
 */
@Data
public class SpuSpecValue {
    /**
     * 主键id
     */
    private long id;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 规格id
     */
    private long specId;

    /**
     * 规格值id
     */
    private String specValueId;

    /**
     * 规格值的图片
     */
    private String url;

    /**
     * 规格值
     */
    private String valueRemark;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 规格信息
     */
    private Spec spec;

    /**
     * 设置规格
     *
     * @param spec 规格信息
     * @return 返回当前对象
     */
    public SpuSpecValue setSpec(Spec spec) {
        this.spec = spec;
        return this;
    }
}

