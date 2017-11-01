package com.lecshop.spu.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品分类实体类
 *
 * Created by LecShop on 2017/6/15.
 */
@Data
public class SpuCategory {

    /**
     * 主键id
     */
    private long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 上级分类id 0 表示没有上级
     */
    private long parentId;

    /**
     * 1 一级分类 2 二级分类 3 三级分类
     */
    private int grade;

    /**
     * 排序
     */
    private int sort;

    /**
     * 店铺id
     */
    private long storeId;

    /**
     * 删除标记  0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 删除时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime delTime;

}
