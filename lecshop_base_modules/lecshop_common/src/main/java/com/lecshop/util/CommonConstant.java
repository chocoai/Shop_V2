package com.lecshop.util;

/**
 * Created by dujinkai on 17/5/2.
 * 公共常量类
 */
public interface CommonConstant {

    /**
     * 开始页
     */
    String START_ROW_NUM = "startRowNum";

    /**
     * 每页大小
     */
    String PAGE_SIZE = "pageSize";

    /**
     * admin端session放置验证码的key
     */
    String ADMIN_LOGIN_KAPTCHA_KEY = "ADMIN_LOGIN_KAPTCHA_KEY";

    /**
     * admin端cookie放置用户名
     */
    String ADMIN_LOGIN_COOKIES_USERNAME_KEY = "ADMIN_LOGIN_COOKIES_USERNAME_KEY";

    /**
     * store端cookie放置用户名
     */
    String STORE_LOGIN_COOKIES_USERNAME_KEY = "STORE_LOGIN_COOKIES_USERNAME_KEY";

    /**
     * 登录成功后把用户放入session中的key
     */
    String STORE_LOGIN_SESSION_KEY = "STORE_LOGIN_SESSION_KEY";

    /**
     * store端session放置验证码的key
     */
    String STORE_LOGIN_KAPTCHA_KEY = "STORE_LOGIN_KAPTCHA_KEY";

    /**
     * 登录成功后把用户信息放入session的key
     */
    String PC_LOGIN_SESSION_KEY = "PC_LOGIN_SESSION_KEY";

    /**
     * admin一级菜单id
     */
    String ADMIN_MENU_FIRSTMENUS = "firstMenus";

    /**
     * admin二级菜单id
     */
    String ADMIN_MENU_SECONDMENUS = "secondMenus";

    /**
     * admin三级菜单id
     */
    String ADMIN_MENU_THIRDMENUS = "thirdMenus";

    /**
     * store一级菜单id
     */
    String STORE_MENU_FIRSTMENUS = "firstMenus";

    /**
     * store二级菜单id
     */
    String STORE_MENU_SECONDMENUS = "secondMenus";

    /**
     * store三级菜单id
     */
    String STORE_MENU_THIRDMENUS = "thirdMenus";

    /**
     * admin端店铺id默认值0
     */
    long ADMIN_STOREID = 0;

    String ENCODE = "UTF-8";

    /**
     * 查询的时候不需要带店铺id过滤
     */
    long QUERY_WITH_NO_STORE = -1;
}
