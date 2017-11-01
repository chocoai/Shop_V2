package com.lecshop.thematicsetting.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 专题设置实体类
 *
 * Created by LecShop on 2017/6/8.
 */
@Data
public class Thematic {

    /**
     * 主键id
     */
    private long id;

    /**
     * 专题标题
     */
    private String title;

    /**
     * seo关键字
     */
    private String seoKeywords;

    /**
     * see描述
     */
    private String seoDesc;

    /**
     * 是否隐藏头尾 0 是  1 否 默认0
     */
    private String hideHeadTail;

    /**
     * 是否启用 0 启用 1 不器用 默认0
     */
    private String isUse;

    /**
     * 店铺id   平台的为0
     */
    private long storeId;

    /**
     * 专题内容
     */
    private String desc;

    /**
     * 删除标记 0 未删除 1 删除 默认0
     */
    private String delFlag;

    /**
     * 生成时间
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
