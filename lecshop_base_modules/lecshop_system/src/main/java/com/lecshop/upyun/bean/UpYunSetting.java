package com.lecshop.upyun.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import com.lecshop.util.UpYunConf;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/8.
 * 又拍云配置类
 */
@Data
public class UpYunSetting {

    /**
     * 主键id
     */
    private long id;

    /**
     * 服务名称
     */
    private String nameSpace;

    /**
     *用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 域名
     */
    private String address;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 获得又拍云的配置
     *
     * @return 返回又拍云配置
     */
    @JsonIgnore
    public UpYunConf getUpYunConf() {
        return UpYunConf.buildUpYunConf(this.nameSpace, this.userName, this.password, this.address);
    }
}
