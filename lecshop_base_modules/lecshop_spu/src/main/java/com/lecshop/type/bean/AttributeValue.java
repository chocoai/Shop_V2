package com.lecshop.type.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.StringUtils;


/**
 * Created by dujinkai on 17/5/11.
 * 属性值
 */
@Data
public class AttributeValue {

    /**
     * 主键id
     */
    private String id;

    /**
     * 属性id
     */
    private String attributeId;

    /**
     * 类型id
     */
    private long typeId;

    /**
     * 属性值
     */
    private String value;


    /**
     * 删除标记 0未删除 1 删除 默认0
     */
    private String delFlag = "0";


    /**
     * 设置自定义id 和 属性id
     *
     * @param attributeId 属性id
     * @param index       索引
     */
    public void setCustomIdAndAttributeId(String attributeId, int index, long typeId) {
        this.attributeId = attributeId;
        this.typeId = typeId;
        setCustomId(index);
    }

    /**
     * 设置自定义id
     * @param index 索引
     */
    public void setCustomId(int index) {
        if (!hasId()) {
            this.id = String.valueOf(System.currentTimeMillis()) + this.attributeId + index;
        }
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
