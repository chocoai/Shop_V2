package com.lecshop.comment.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.sku.bean.Sku;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 17/5/24.
 * 评论实体
 */
@Data
public class Comment {

    /**
     * 主键id
     */
    private long id;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 发表人名称
     */
    private String customerName;

    /**
     * 单品id
     */
    private String skuId;

    /**
     * 是否匿名  0 否 1是  默认0
     */
    private String isAnonymous;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 描述相符 评分1到5分
     */
    private int descScore;

    /**
     * 卖家服务评分 1到5分
     */
    private int sellerScore;

    /**
     * 物流评分 1到5分
     */
    private int logisticsScore;

    /**
     * 是否有图片  0 没有 1 有 默认0
     */
    private String hasPic;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 单品信息
     */
    private Sku sku;

    /**
     * 评论图片
     */
    private List<CommentPic> commentPics;
}
