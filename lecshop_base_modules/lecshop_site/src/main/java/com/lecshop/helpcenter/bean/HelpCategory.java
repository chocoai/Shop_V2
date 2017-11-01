package com.lecshop.helpcenter.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 帮助分类实体类
 *
 * Created by LecShop on 2017/5/23.
 */
@Data
public class HelpCategory {

    /**
     * 主键id
     */
    private long id;

    /**
     * 帮助分类名称
     */
    private String name;

    /**
     * 排序 数值越小 排序越前
     */
    private int sort;
    /**
     * 是否显示  0 显示，1 不显示。默认0
     */
    private int isShow;

    /**
     * 删除标记 0 未删除，1 删除。默认0
     */
    private int delFlag = 0;

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
