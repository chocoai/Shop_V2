package com.lecshop.type.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dujinkai on 17/5/10.
 * 商品类型
 */
@Data
public class Type {

    /**
     * 主键id
     */
    private long id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 删除标记 0未删除 1 删除 默认0
     */
    private String delFlag = "0";

    /**
     * 创建人
     */
    private String createName;

    /**
     * 修改人
     */
    private String modifyName;

    /**
     * 删除人
     */
    private String delName;

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
     * 类型关联的属性
     */
    private List<Attribute> attributes;

    /**
     * 类型关联的规格
     */
    private List<TypeSpec> typeSpecs;

    /**
     * 设置属性的类型id
     */
    public void setAttributesTypeId() {
        if (CollectionUtils.isEmpty(this.attributes)) {
            return;
        }
        IntStream.range(0, this.attributes.size()).forEach(index -> this.attributes.get(index).setCustomIdAndTypeId(this.id, index));
    }

    /**
     * 设置类型与规格关联的类型id
     */
    public void setTypeSpecTypeId() {
        if (CollectionUtils.isEmpty(this.typeSpecs)) {
            return;
        }
        typeSpecs.stream().forEach(typeSpec -> typeSpec.setTypeId(this.id));
    }

    /**
     * 设置新增时候的默认值
     *
     * @param name 操作人
     */
    public Type setDefaultValuesForAdd(String name) {
        this.createName = name;
        this.createTime = LocalDateTime.now();
        this.delFlag = "0";
        return this;
    }

    /**
     * 设置修改时候的默认值
     *
     * @param name 操作人
     * @return 返回类型信息
     */
    public Type setDefalutValuesForModify(String name) {
        this.modifyName = name;
        this.modifyTime = LocalDateTime.now();
        return this;
    }


    /**
     * 构造删除的实体
     *
     * @param name 操作人
     * @return 返回删除实体
     */
    public static Type buildForDelete(long id, String name) {
        Type type = new Type();
        type.id = id;
        type.delFlag = "1";
        type.delName = name;
        type.delTime = LocalDateTime.now();
        return type;
    }
}
