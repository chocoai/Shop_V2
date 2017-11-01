package com.lecshop.storerole.bean;

import lombok.Data;

/**
 * 角色和管理员关联表的实体类
 */
@Data
public class RoleAndCustomer {
    /**
     * 管理员id
     */
    private long customerId;
    /**
     * 角色id
     */
    private long roleId;
    /**
     * 用户名
     */
    private String userName;

    public RoleAndCustomer getRoleAndCustomer(long customerId, long roleId) {
        this.customerId = customerId;
        this.roleId = roleId;
        return this;
    }
}
