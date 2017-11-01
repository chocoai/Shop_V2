package com.lecshop.helpcenter.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 服务支持实体类
 *
 * Created by LecShop on 2017/6/2.
 */
@Data
public class ServiceSupport {

    /**
     * 主键id
     */
    private long id;

    /**
     * 服务支持的名称
     */
    private String name;

    /**
     * 服务支持的图片地址
     */
    private String url;

    /**
     * 所属帮助id
     */
    private long helpId;

    /**
     * 删除标记  0 未删除 1删除  默认0
     */
    private String delFlag;

    /**
     * 创建者名称
     */
    private String createName;

    /**
     * 修改者名称
     */
    private String modifyName;

    /**
     * 删除者名称
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
     * 帮助名称
     */
    private String helpName;

}
