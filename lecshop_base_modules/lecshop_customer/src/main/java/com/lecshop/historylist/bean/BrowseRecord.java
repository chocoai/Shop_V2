package com.lecshop.historylist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.sku.bean.Sku;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 浏览历史实体类
 *
 * Created by LecShop on 2017/7/4.
 */
@Data
public class BrowseRecord {

    /**
     * 主键id
     */
    private long id;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 单品id
     */
    private String skuId;

    /**
     * 删除标记  0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 单品
     */
    private Sku sku;

    public String getGroupByTime() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(this.createTime);
    }

}
