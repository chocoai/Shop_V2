package com.lecshop.articlemanage.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 栏目列表实体类
 *
 * @author sunluyang on 2017/5/22.
 */
@Data
public class ColumnList {
    /**
     * 主键id
     */
    private long id;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 上级分类id   0 表示无上级
     */
    private long parentId;
    /**
     * 排序 数值越小排序越前
     */
    private int sort;
    /**
     * 前端是否显示  0 显示 1 不显示 默认0
     */
    private String isShow;
    /**
     * 删除标记  0 未删除 1 删除  默认0
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
