package com.lecshop.spec.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.specvalue.bean.SpecValue;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 17/5/8.
 * 商品规格
 */
@Data
public class Spec {

    /**
     * 主键id
     */
    private long id;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 规格别名
     */
    private String nickName;

    /**
     * 删除标记 0 未删除  1 删除 默认0
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
     * 规格下的规格值
     */
    private List<SpecValue> specValues;

    /**
     * 设置新增的默认值
     *
     * @param name 操作人名称
     */
    public Spec setDetaultValuesForAdd(String name) {
        this.createName = name;
        this.createTime = LocalDateTime.now();
        this.delFlag = "0";

        if (!CollectionUtils.isEmpty(this.specValues)) {
            specValues.stream().forEach(specValue -> specValue.setDefaultValuesForAdd(name));
        }
        return this;
    }

    /**
     * 设置修改的默认值
     * @param name 操作人
     */
    public Spec setDetaultValuesForUpdate(String name) {
        this.modifyName = name;
        this.modifyTime = LocalDateTime.now();

        if (!CollectionUtils.isEmpty(this.specValues)) {
            specValues.stream().forEach(specValue -> specValue.setDefaultValuesForAdd(name));
        }
        return this;
    }

    /**
     * 构造删除实体
     *
     * @param id      删除规格的id
     * @param delName 删除人
     * @return 返回删除规格的实体
     */
    public static Spec buildForDelete(long id, String delName) {
        Spec spec = new Spec();
        spec.id = id;
        spec.delFlag = "1";
        spec.delName = delName;
        spec.delTime = LocalDateTime.now();
        return spec;
    }
}
