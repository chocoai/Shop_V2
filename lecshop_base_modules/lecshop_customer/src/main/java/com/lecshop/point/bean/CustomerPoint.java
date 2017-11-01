package com.lecshop.point.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/25.
 * 会员积分
 */
@Data
public class CustomerPoint {

    /**
     * 主键id
     */
    private long id;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 积分详情
     */
    private String detail;

    /**
     * 积分类型 1 收入 2支出
     */
    private String type;

    /**
     * 积分数量 正数表示增加，负数表示减少
     */
    private int point;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 设置管理员新增积分时候的默认值
     *
     * @return 返回积分信息
     */
    public CustomerPoint setValuesForManagerAdd() {
        if (this.point >= 0) {
            this.type = "1";
        } else {
            this.type = "0";
        }
        return this;
    }
}
