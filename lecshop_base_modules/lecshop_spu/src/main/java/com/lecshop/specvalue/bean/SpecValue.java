package com.lecshop.specvalue.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/8.
 * 规格值
 */
@Data
public class SpecValue {

    /**
     * 主键id
     */
    private String id;

    /**
     * 规格值所属的规格id
     */
    private long specId;

    /**
     * 规格值的名称
     */
    private String name;

    /**
     * 排序 数值越小 排序越前
     */
    private int sort;

    /**
     * 删除标记  0 未删除 1 删除
     */
    private String delFlag;

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
     * 设置新增时候的默认值
     *
     * @param name 操作人
     */
    public void setDefaultValuesForAdd(String name) {
        this.createName = name;
        this.createTime = LocalDateTime.now();
        this.delFlag = "0";
    }

    /**
     * 设置自定义的主键id
     *
     * @param index 索引
     */
    public void setCustomId(int index) {
        if (!hasId()) {
            this.id = String.valueOf(System.currentTimeMillis()) + this.specId + index;
        }
    }

    /**
     * 判断是否已经存在主键
     *
     * @return 存在返回true  不存在返回false
     */
    @JsonIgnore
    public boolean hasId() {
        return !StringUtils.isEmpty(this.id);
    }


    /**
     * 构造新增实体
     *
     * @param name 操作人名称
     * @return 返回规格值新增实体
     */
    public SpecValue setDefaultValuesForAdd(SpecValue specValue, String name) {
        specValue.createName = name;
        specValue.delFlag = "0";
        specValue.createTime = LocalDateTime.now();
        specValue.setCustomId(0);
        return specValue;
    }

    /**
     * 构造删除实体
     *
     * @param specId  规格id
     * @param delName 删除人
     * @return 返回删除实体
     */
    public static SpecValue buildForDelete(long specId, String delName) {
        SpecValue specValue = new SpecValue();
        specValue.specId = specId;
        specValue.delName = delName;
        specValue.delTime = LocalDateTime.now();
        specValue.delFlag = "1";
        return specValue;
    }
}
