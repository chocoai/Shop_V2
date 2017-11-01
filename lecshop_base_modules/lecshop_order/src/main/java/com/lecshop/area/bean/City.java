package com.lecshop.area.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/15.
 * 市
 */
@Data
public class City {

    /**
     * 主键id
     */
    private long id;

    /**
     * 市名称
     */
    private String name;

    /**
     * 省份id
     */
    private long provinceId;

    /**
     * 排序  数值越小 排序越前
     */
    private int sort;


    /**
     * 删除标记 0 未删除 1 删除 默认0
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


    /**
     * 设置添加市的默认
     *
     * @return 返回市
     */
    public City setDefaultValuesForAdd() {
        this.createTime = LocalDateTime.now();
        this.delFlag = "0";
        return this;
    }
}
