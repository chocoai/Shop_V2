package com.lecshop.pointset.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/23.
 * 积分设置实体
 */
@Data
public class PointSeting {

    /**
     * 主键id
     */
    private long id;

    /**
     * 是否开启 0未开启 1开启 默认0未开启
     */
    private String isOpen;

    /**
     * 邮箱验证积分（只有第一次验证的时候赠送）
     */
    private int emailPoint;

    /**
     * 手机验证赠送积分（只有第一次验证的时候赠送）
     */
    private int mobilePoint;

    /**
     * 发表评论赠送积分
     */
    private int commentPoint;

    /**
     * 使用多少积分
     */
    private int usePoint;

    /**
     * 抵多少钱
     */
    private int offsetMoney;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;
}
