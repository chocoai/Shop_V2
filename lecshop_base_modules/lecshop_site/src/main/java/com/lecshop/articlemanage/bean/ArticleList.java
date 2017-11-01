package com.lecshop.articlemanage.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文章信息实体类
 *
 * @author sunluyang on 2017/5/22.
 */
@Data
public class ArticleList {
    /**
     * 主键id
     */
    private long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 文章描述
     */
    private String desc;

    /**
     * 文章所属栏目id
     */
    private long columnId;
    /**
     * 文章栏目名称
     */
    private String columnName;
    /**
     * 排序 数值越小 排序越前
     */
    private int sort;
    /**
     * 是否发布 0 发布 1 不发布  默认0
     */
    private String isRelease;
    /**
     * seo关键字
     */
    private String seoKeywords;
    /**
     * seo描述
     */
    private String seoDesc;
    /**
     * 删除标记 0 未删除 1 删除
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
