package com.lecshop.category.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 17/5/11.
 * 商品分类
 */
@Data
public class Category {

    /**
     * 主键id
     */
    private long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父级分类id 如果是0 则说明没有父级分类
     */
    private long parentId;

    /**
     * 分类关联的类型id(只有三级分类才有类型)
     */
    private long typeId;

    /**
     * 层级 1 一级  2二级  3 三级
     */
    private int grade;

    /**
     * 分类扣率(只有三级分类才有分类扣率)
     */
    private BigDecimal rate;

    /**
     * 排序 数值越前 则 排序越前
     */
    private int sort;


    /**
     * 删除标记 0 未删除 1删除 默认0
     */
    private String delFlag = "0";

    /**
     * 创建者
     */
    private String createName;

    /**
     * 修改者
     */
    private String modifyName;

    /**
     * 删除者
     */
    private String delName;

    /**
     * 子分类
     */
    private List<Category> childCateGory;

    /**
     * 父级分类
     */
    @JsonIgnore
    private Category parentCategory;

    public Category setChildCateGory(List<Category> cateGory) {
        this.childCateGory = cateGory;
        return this;
    }

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
     * 设置添加分类的默认值
     *
     * @param name 操作人
     * @return 返回分类
     */
    public Category setDefaultsForAdd(String name) {
        this.createName = name;
        this.delFlag = "0";
        this.createTime = LocalDateTime.now();
        return this;
    }

    /**
     * 构造删除实体
     *
     * @param id   分类id
     * @param name 操作人
     * @return 返回删除实体
     */
    public static Category buildForDelete(long id, String name) {
        Category category = new Category();
        category.id = id;
        category.delFlag = "1";
        category.delName = name;
        category.delTime = LocalDateTime.now();
        return category;
    }

    /**
     * 设置修改分类的默认值
     *
     * @param name 操作人
     * @return 返回分类
     */
    public Category setDefaultValuesForModify(String name) {
        this.modifyName = name;
        this.modifyTime = LocalDateTime.now();
        return this;
    }

    /**
     * @return
     */
    @JsonIgnore
    public boolean isFirstCategory() {
        return this.grade == 1;
    }
}

