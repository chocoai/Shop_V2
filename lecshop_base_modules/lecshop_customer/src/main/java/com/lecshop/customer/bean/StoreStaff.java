package com.lecshop.customer.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import com.lecshop.util.MD5Utils;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sunluyang on 2017/6/12.
 */
@Data
public class StoreStaff {
    /**
     * 主键id
     */
    private long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户的真实姓名
     */
    private String releName;

    /**
     * 店铺id  平台的为0  默认为平台
     */
    private long storeId;

    /**
     * 1 普通用户 2 商家店铺用户 3 店铺员工
     */
    private String type;

    /**
     * 用户状态 1 正常 2 冻结 3 未启用 默认1
     */
    private String status;

    /**
     * 角色id
     */
    private long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime lastLoginTime;

    public StoreStaff getAddStoreStaffData(StoreStaff storeStaff, long storeId, String type) {
        storeStaff.setStoreId(storeId);
        storeStaff.setType(type);
        storeStaff.setPassword(MD5Utils.getInstance().createMd5(storeStaff.getPassword()));
        return storeStaff;
    }
}
