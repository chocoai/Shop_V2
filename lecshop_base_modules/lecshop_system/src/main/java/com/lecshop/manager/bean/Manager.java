package com.lecshop.manager.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.manager.util.ManagerConstant;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import com.lecshop.util.MD5Utils;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/2.
 * <p>
 * 管理员实体
 */
@Data
public class Manager {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否启用
     * 0 启用 1禁用 默认0启用
     */
    private String isUse = ManagerConstant.MANAGER_USE;

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
     * 登录时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime loginTime;

    /**
     * 判断密码是否正确
     *
     * @param password 用户输入的密码
     * @return 正确返回true  不正确返回false
     */
    @JsonIgnore
    public boolean isPasswordRight(String password) {
        return StringUtils.isEmpty(password) ? false : MD5Utils.getInstance().createMd5(password).equals(this.password);
    }

    /**
     * 清除密码
     */
    public void clearPassword() {
        this.password = "***********";
    }
}

