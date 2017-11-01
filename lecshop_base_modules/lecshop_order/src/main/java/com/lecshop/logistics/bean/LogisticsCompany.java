package com.lecshop.logistics.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/15.
 * 物流公司
 */
@Data
public class LogisticsCompany {

    /**
     * 主键id
     */
    private long id;

    /**
     * 物流公司名称
     */
    private String name;

    /**
     * 物流公司代码
     */
    private String code;

    /**
     * 快递100 号
     */
    private String express100Code;

    /**
     * 店铺id 平台的默认为0
     */
    private long storeId;

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
     * 新增物流的时候 设置默认值
     *
     * @param storeId 店铺id
     * @return 返回当前对象
     */
    public LogisticsCompany setDefaultValuesForAdd(long storeId) {
        this.storeId = storeId;
        this.delFlag = "1";
        this.createTime = LocalDateTime.now();
        return this;
    }

    /**
     * 修改物流的时候 设置默认值
     *
     * @param storeId 店铺id
     * @return 返回物流信息
     */
    public LogisticsCompany setDefaultValuesForUpdate(long storeId) {
        this.storeId = storeId;
        this.modifyTime = LocalDateTime.now();
        return this;
    }
}
