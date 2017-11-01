package com.lecshop.manager.util;

/**
 * Created by dujinkai on 17/5/2.
 * 管理员常量类
 */
public interface ManagerConstant {

    /**
     * 管理员启用
     */
    String MANAGER_USE = "0";

    /**
     * 管理员禁用
     */
    String MANAGER_NOT_USE = "1";

    /**
     * 登录成功后把用户放入session中的key
     */
    String ADMIN_LOGIN_SESSION_KEY = "ADMIN_LOGIN_SESSION_KEY";
}
