package com.lecshop.type.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dujinkai on 17/5/11.
 * 属性
 */
@Data
public class Attribute {

    /**
     * 主键id
     */
    private String id;

    /**
     * 属性的类型id
     */
    private long typeId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 排序
     */
    private int sort;

    /**
     * 删除标记 0未删除 1 删除 默认0
     */
    private String delFlag = "0";

    /**
     * 属性关联的属性值
     */
    private List<AttributeValue> attributeValues;

    /**
     * 设置自定义id 和 类型id
     *
     * @param typeId 类型id
     * @param index  索引
     */
    public void setCustomIdAndTypeId(long typeId, int index) {
        this.typeId = typeId;
        setCustomId(index);
    }

    /**
     * 设置自定义id
     * @param index 索引
     */
    public void setCustomId(int index) {
        if (!hasId()) {
            this.id = String.valueOf(System.currentTimeMillis()) + this.typeId + index;
        }
    }

    /**
     * 设置属性值的id
     */
    public void setAttributeValuesId() {
        if (CollectionUtils.isEmpty(this.attributeValues)) {
            return;
        }
        IntStream.range(0, this.attributeValues.size()).forEach(index -> this.attributeValues.get(index).setCustomIdAndAttributeId(this.id, index, this.typeId));
    }

    /**
     * 判断是否已经存在主键
     * @return 存在返回true  不存在返回false
     */
    @JsonIgnore
    public boolean hasId() {
        return !StringUtils.isEmpty(this.id);
    }
}
